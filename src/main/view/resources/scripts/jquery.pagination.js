/**
 * jquery.pagination.js v3.20190309
 * dependency: jQuery.js, devutility.js
 * @license: MIT (c) Aldwin Su. https://github.com/eagle6688
 */

(function ($, window, document, undefined) {
    var pluginName = 'pagination';

    var defaults = {
        totalRecords: 0,
        pageIndex: 1,
        pageSize: 10,
        visiblePagesCount: 7, //Visible pages count.
        changingType: 'ajax', //Changing page can cause two action types 'jump' and 'ajax'.
        firstButtonName: 'First', //Text of 'first' button.
        firstButtonClass: 'first', //Style of 'first' button.
        prevButtonName: 'Previous', //Button's text of Previous button.
        prevButtonClass: 'prev', //Button's style of Previous.
        nextButtonName: 'Next', //Button's text of Next.
        nextButtonClass: 'next', //Button's style of Next.
        lastButtonName: 'Last', //Button's text of Last.
        lastButtonClass: 'last', //Button's style of Last.
        buttonClass: 'page-item', //Class for each button.
        buttonAClass: 'page-link', //Class for lable <a> in button.
        disabledButtonClass: 'disabled', //Style of disabled button.
        currentButtonClass: 'active', //Style of current button.
        paginationClass: 'pagination', //Style of ul dom.
        onPageClick: function (pageIndex) {},
        onReload: function (options) {}
    };

    var config = {
        buttonDataName: 'pageIndex'
    };

    function Plugin(element, options) {
        this.$element = $(element);
        this.options = $.extend({}, defaults, options);
        this._init();
    }

    Plugin.prototype.constructor = Plugin;

    /* Init */

    Plugin.prototype._init = function () {
        if (!this._verify()) {
            return;
        }

        this.pagesCount = calculatePagesCount(this.options.totalRecords, this.options.pageSize);
        this._initOptions();

        this.displayPagesCount = getDisplayPagesCount(this.pagesCount, this.options.visiblePagesCount);
        this._initPagination();
    };

    Plugin.prototype._verify = function () {
        var options = this.options;

        if (isNaN(options.totalRecords) || options.totalRecords < 0) {
            console.error('Invalid "totalRecords"!');
            return false;
        }

        if (isNaN(options.pageIndex) || options.pageIndex < 1) {
            console.error('Invalid "pageIndex"!');
            return false;
        }

        if (isNaN(options.pageSize) || options.pageSize < 1) {
            console.error('Invalid "pageSize"!');
            return false;
        }

        if (isNaN(options.visiblePagesCount) || options.visiblePagesCount < 1) {
            console.error('Invalid "visiblePagesCount"!');
            return false;
        }

        return true;
    };

    Plugin.prototype._initOptions = function () {
        if (this.pagesCount == 0) {
            this.options.pageIndex = defaults.pageIndex;
            this.options.visiblePagesCount = defaults.visiblePagesCount;
            return;
        }

        if (this.options.pageIndex > this.pagesCount) {
            this.options.pageIndex = this.pagesCount;
        }

        if (this.options.visiblePagesCount > this.pagesCount) {
            this.options.visiblePagesCount = this.pagesCount;
        }
    };

    Plugin.prototype._initPagination = function () {
        this.$pagination = $('<ul></ul>');

        if (this.options.paginationClass) {
            this.$pagination.addClass(this.options.paginationClass);
        }

        this._loadPagination();
        this.$element.empty().append(this.$pagination);
    };

    /* Init end */

    /* Bind methods */

    Plugin.prototype._bind = function () {
        this._bindButtons();
        this._bindPageButtons();
    };

    Plugin.prototype._bindButtons = function () {
        this._bindFirstButton(this.$firstBtn);
        this._bindPrevButton(this.$prevBtn);
        this._bindNextButton(this.$nextBtn);
        this._bindLastButton(this.$lastBtn);
    };

    Plugin.prototype._bindPageButtons = function () {
        var self = this;

        this.$pagination.children('li').each(function () {
            var $button = $(this);
            var data = $button.data(config.buttonDataName);

            if (data) {
                var pageIndex = ~~data;

                if (self.options.pageIndex === pageIndex) {
                    if (!(isButton($button, self.options.firstButtonName) || isButton($button, self.options.lastButtonName))) {
                        $button.addClass(self.options.currentButtonClass);
                    }
                } else {
                    $button.click(function () {
                        self._changePageIndex(pageIndex);
                    });
                }
            }
        });
    };

    Plugin.prototype._bindFirstButton = function ($button) {
        var self = this;

        if ($button === null) {
            return;
        }

        if (this.options.pageIndex === 1) {
            $button.addClass(this.options.disabledButtonClass);
        } else {
            $button.click(function () {
                self._changePageIndex(1);
            });
        }
    };

    Plugin.prototype._bindPrevButton = function ($button) {
        var self = this;

        if ($button === null) {
            return;
        }

        if (this.options.pageIndex === 1) {
            $button.addClass(this.options.disabledButtonClass);
        } else {
            $button.click(function () {
                self._changePageIndex(self.options.pageIndex - 1);
            });
        }
    };

    Plugin.prototype._bindNextButton = function ($button) {
        var self = this;

        if ($button === null) {
            return;
        }

        if (this.options.pageIndex === this.pagesCount || this.pagesCount === 0) {
            $button.addClass(this.options.disabledButtonClass);
        } else {
            $button.click(function () {
                self._changePageIndex(self.options.pageIndex + 1);
            });
        }
    };

    Plugin.prototype._bindLastButton = function ($button) {
        var self = this;

        if ($button === null) {
            return;
        }

        if (this.options.pageIndex === this.pagesCount || this.pagesCount === 0) {
            $button.addClass(this.options.disabledButtonClass);
        } else {
            $button.click(function () {
                self._changePageIndex(self.pagesCount);
            });
        }
    };

    /* Bind methods end */

    /* Methods */

    Plugin.prototype._loadPagination = function () {
        this.$pagination.empty();
        this._fillPagination();
        this._bind();
    };

    Plugin.prototype._fillPagination = function () {
        this.$firstBtn = this._createPageButton(this.options.firstButtonName, this.options.firstButtonClass);
        this.$prevBtn = this._createPageButton(this.options.prevButtonName, this.options.prevButtonClass);
        this.$pagination.append(this.$firstBtn, this.$prevBtn);

        this._fillPageButtons();

        this.$nextBtn = this._createPageButton(this.options.nextButtonName, this.options.nextButtonClass);
        this.$lastBtn = this._createPageButton(this.options.lastButtonName, this.options.lastButtonClass);
        this.$pagination.append(this.$nextBtn, this.$lastBtn);
    };

    Plugin.prototype._fillPageButtons = function () {
        var pagesRegion = getPagesRegion(this.options.pageIndex, this.displayPagesCount, this.pagesCount);
        this._createPageButtons(pagesRegion.start, pagesRegion.end);
    };

    Plugin.prototype._createPageButtons = function (start, end) {
        for (var i = start; i <= end; i++) {
            var $pageButton = this._createPageButton(i).data(config.buttonDataName, i);
            this.$pagination.append($pageButton);
        }
    };

    Plugin.prototype._createPageButton = function (text, btnClassName) {
        var btnClasses = [];
        var aClasses = [];

        if (this.options.buttonClass) {
            btnClasses.push(this.options.buttonClass);
        }

        if (btnClassName) {
            btnClasses.push(btnClassName);
        }

        if (this.options.buttonAClass) {
            aClasses.push(this.options.buttonAClass);
        }

        return createButton(text, btnClasses, aClasses);
    };

    Plugin.prototype._changePageIndex = function (pageIndex) {
        this.options.pageIndex = pageIndex;
        this._onPageClick(pageIndex);
        this._loadPagination();
    };

    /* Methods end */

    /* Events */

    Plugin.prototype._onPageClick = function (pageIndex) {
        if (this.options.onPageClick) {
            this.options.onPageClick(pageIndex);
        }
    };

    Plugin.prototype._onReload = function () {
        if (this.options.onReload) {
            this.options.onReload(this.options);
        }
    };

    /* Events end */

    /* Functions */

    var calculatePagesCount = function (totalRecords, pageSize) {
        var count = parseInt(totalRecords / pageSize);

        if (totalRecords % pageSize > 0) {
            count++;
        }

        return count;
    };

    var getDisplayPagesCount = function (pagesCount, visiblePagesCount) {
        return Math.min(pagesCount, visiblePagesCount);
    };

    var getPagesRegion = function (pageIndex, displayPagesCount, pagesCount) {
        var result = {
            start: 0,
            end: 0
        };

        var half = parseInt(displayPagesCount / 2);

        if (pageIndex === 1 || pageIndex <= half) {
            result.start = 1;
            result.end = displayPagesCount;
            return result;
        }

        if (pageIndex === pagesCount || pageIndex >= pagesCount - half) {
            result.start = pagesCount - displayPagesCount + 1;
            result.end = pagesCount;
            return result;
        }

        result.start = pageIndex - half;
        result.end = pageIndex + half;

        if (displayPagesCount % 2 === 0) {
            result.start += 1;
        }

        return result;
    };

    var createButton = function (text, btnClasses, aClasses) {
        if (text === null || text === '' || text === undefined) {
            return null;
        }

        var $button = $('<li></li>');

        if (btnClasses) {
            for (var index in btnClasses) {
                $button.addClass(btnClasses[index]);
            }
        }

        var $aLink = $('<a href="javascript:void(0);"></a>').html(text);

        if (aClasses) {
            for (var index in aClasses) {
                $aLink.addClass(aClasses[index]);
            }
        }

        return $button.append($aLink);
    };

    var isButton = function ($button, text) {
        return $button.children('a').html() === text;
    };

    /* Functions end */

    /* Public methods */

    Plugin.prototype.getOption = function (name) {
        return this.options[name];
    };

    Plugin.prototype.reload = function (options) {
        if (!devutility.options.isChanged(this.options, options)) {
            return;
        }

        this.options = $.extend({}, this.options, options);
        this._init();
        this._onReload();
    };

    Plugin.prototype.changeTotalRecords = function (totalRecords) {
        this.reload({
            totalRecords: totalRecords
        });
    };

    Plugin.prototype.changePageIndex = function (pageIndex) {
        this.reload({
            pageIndex: pageIndex
        });
    };

    Plugin.prototype.changePageSize = function (pageSize) {
        this.reload({
            pageSize: pageSize
        });
    };

    /* Public methods end */

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new Plugin(this, options));
            }
        });
    };
})(jQuery, window, document);