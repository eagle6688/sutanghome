/**
 * @license Popuper.js v20190224
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'Popuper';

    var defaults = {
        selector: '', //Selector for popup dom.
        zIndex: 101, //zIndex for popup dom.
        background: null //Background object.
    };

    function Plugin(options) {
        this.options = $.extend({}, defaults, options);
        this._init();
        this._bind();
    }

    Plugin.prototype.constructor = Plugin;

    Plugin.prototype._init = function () {
        this.$element = $(this.options.selector);
        this.background = this._background();
        this._initElement();
    };

    Plugin.prototype._initElement = function () {
        this.$element.css({
            'zIndex': this.options.zIndex,
            'position': 'absolute',
            'display': 'none'
        });

        this._setPosition();
    };

    Plugin.prototype._bind = function () {
        var self = this;

        $(window).resize(function () {
            self._setPosition();
        });
    };

    Plugin.prototype._setPosition = function () {
        var width = this.$element.width();
        var height = this.$element.height();
        var marginTop = -(height / 2);
        var marginLeft = -(width / 2);

        this.$element.css({
            'left': '50%',
            'top': '50%',
            'margin-top': marginTop,
            'margin-left': marginLeft
        });
    };

    Plugin.prototype._background = function () {
        if (this.options.background) {
            return this.options.background;
        }

        if (typeof Background == 'function') {
            this.options.background = new Background({
                'zIndex': this.options.zIndex - 1
            });

            return this.options.background;
        }

        console.error('Parameter "background" cannot be null!');
        return null;
    };

    /* Public methods */

    Plugin.prototype.show = function () {
        if (this.$element.css('display') == 'none') {
            this.$element.show(arguments);
        }
    };

    Plugin.prototype.showAll = function () {
        this.options.background.show(arguments);
        this.show();
    };

    Plugin.prototype.hide = function () {
        if (this.$element.css('display') != 'none') {
            this.$element.hide(arguments);
        }
    };

    Plugin.prototype.hideAll = function () {
        this.hide();
        this.options.background.hide(arguments);
    };

    /* Public methods end */

    window[pluginName] = Plugin;
})(jQuery, window, document);