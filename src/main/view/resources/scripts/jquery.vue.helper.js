/**
 * @license jquery.vue.helper.js v20190202
 * (c) 2010-2019 Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'vueHelper';

    var defaults = {
        url: '',
        requestType: 'GET',
        requestData: null,
        viewModel: null,
        vueConfig: null,
        pageSize: 10,
        autoLoad: true,
        loadingDom: '', //Dom displays when data are loading.
        beforeLoadData: function (data) {},
        afterLoadData: function (data) {},
        onReload: function (data) {}
    };

    var events = {
        init: 1,
        reload: 2,
        changePage: 3
    };

    function Plugin(element, options) {
        this.element = element;
        this.$element = $(element);
        this.selector = '#' + this.$element.attr('id');
        this.options = $.extend({}, defaults, options);
        this._init();
    }

    Plugin.prototype.constructor = Plugin;

    Plugin.prototype._init = function () {
        this.event = events.init;
        this._initData();
        this._initVue();
        this._load();
    };

    Plugin.prototype._initData = function () {
        this.pageIndex = 1;

        this.viewModel = {
            data: [],
            count: 0
        };
    };

    Plugin.prototype._initVue = function () {
        var defaults = {
            el: this.selector,
            data: this.viewModel
        };

        var options = $.extend({}, this.options.vueConfig, defaults);
        this.vue = new Vue(options);
    };

    Plugin.prototype._load = function () {
        if (this.options.autoLoad) {
            this._loadData();
        }
    };

    Plugin.prototype._loadData = function () {
        if (this.options.viewModel) {
            this._loadViewModel(this.options.viewModel);
            this.options.viewModel = null;
        } else if (this.options.url) {
            this._requestData();
        }
    };

    Plugin.prototype._requestData = function () {
        var self = this;
        var url = getPageUrl(this.options.url, this.pageIndex, this.options.pageSize);
        this._displayDom(this.options.loadingDom);

        this._ajax(url, function (data) {
            self._loadViewModel(data);
            self._hideDom(self.options.loadingDom);
        });
    };

    Plugin.prototype._loadViewModel = function (data) {
        this._onReload(data);
        this._beforeLoadData(data);
        this._setViewModel(data);
        this._afterLoadData(data);
    };

    Plugin.prototype._setViewModel = function (data) {
        this.vue.count = data.count;
        this.vue.data = data.data;
    };

    Plugin.prototype._ajax = function (url, success) {
        $.ajax({
            url: url,
            dataType: 'json',
            type: this.options.requestType,
            data: this.options.requestData,
            cache: false,
            success: function (data) {
                if (success) {
                    success(data);
                }
            }
        });
    };

    Plugin.prototype._reloadOptions = function (options) {
        var newOptions = $.extend(true, {}, options);
        this.options = $.extend({}, this.options, newOptions);
    };

    Plugin.prototype._reloadOption = function (name, value) {
        if (this.options.hasOwnProperty(name)) {
            this.options[name] = clone(value);
        }
    };

    Plugin.prototype._displayDom = function (selector) {
        if (selector && $(selector).length > 0) {
            $(selector).show();
        }
    };

    Plugin.prototype._hideDom = function (selector) {
        if (selector && $(selector).length > 0) {
            $(selector).hide();
        }
    };

    //events

    Plugin.prototype._beforeLoadData = function (data) {
        if (this.options.beforeLoadData) {
            this.options.beforeLoadData(data);
        }
    };

    Plugin.prototype._afterLoadData = function (data) {
        if (this.options.afterLoadData) {
            this.options.afterLoadData(data);
        }
    };

    Plugin.prototype._onReload = function (data) {
        if (this.event !== events.init && this.event !== events.reload) {
            return;
        }

        if (this.options.onReload) {
            this.options.onReload(data);
        }
    };

    //inner functions

    var getPageUrl = function (url, pageIndex, pageSize) {
        if (url.indexOf('?') > 0) {
            url += '&';
        } else {
            url += '?';
        }

        var skip = (pageIndex - 1) * pageSize;
        url += 'pageIndex=' + pageIndex;
        url += '&pageSize=' + pageSize;
        url += '&skip=' + skip;
        return url;
    };

    var clone = function (value) {
        var obj = {
            value: value
        };

        var newObject = $.extend(true, {}, obj);
        return newObject.value;
    };

    //exported methods

    var restore = function (vueObject) {
        var result = null;
        var type = Object.prototype.toString.call(vueObject);

        switch (type) {
            case '[object Array]':
                result = toArray(vueObject);
                break;

            case '[object Object]':
                result = toObject(vueObject);
                break;

            default:
                result = vueObject;
                break;
        }

        function toArray(vueArray) {
            var array = [];

            for (var index in vueArray) {
                var item = restore(vueArray[index]);
                array.push(item);
            }

            return array;
        }

        function toObject(vueObject) {
            var obj = new object();

            for (var index in vueObject) {
                var item = restore(vueObject[index]);
                obj[index] = item;
            }

            return obj;
        }

        return result;
    };

    Plugin.prototype.changePage = function (pageIndex) {
        this.event = events.changePage;
        this.pageIndex = pageIndex;
        this._requestData();
    };

    Plugin.prototype.changePageAndOptions = function (pageIndex, options) {
        if (options) {
            this._reloadOptions(options);
        }

        this.changePage(pageIndex);
    };

    Plugin.prototype.reload = function () {
        this.event = events.reload;

        if (arguments.length === 1) {
            var type = typeof arguments[0];

            switch (type) {
                case 'number':
                    this.pageIndex = arguments[0];
                    break;

                case 'object':
                    this._reloadOptions(arguments[0]);
                    break;

                default:
                    break;
            }
        }

        if (arguments.length === 2) {
            this._reloadOption(arguments[0], arguments[1]);
        }

        this._loadData();
    };

    Plugin.prototype.get = function (name) {
        return this.options[name];
    };

    $[pluginName] = {
        restore: restore
    };

    $.fn[pluginName] = function (options) {
        return new Plugin(this, options);
    };
})(jQuery, window, document);