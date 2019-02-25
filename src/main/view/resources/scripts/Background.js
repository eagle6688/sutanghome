/**
 * @license Background.js v20190224
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'Background';

    var defaults = {
        backgroundId: 'background', //Id for background, default is 'background'.
        zIndex: 100 //zIndex for background.
    };

    function Plugin(options) {
        this.options = $.extend({}, defaults, options);
        this._init();
        this._bind();
    }

    Plugin.prototype.constructor = Plugin;

    Plugin.prototype._init = function () {
        this.id = this._getId();
        this.$element = $(document.getElementById(this.id));

        if (this.$element.length == 0) {
            this._createBackground();
        }
    };

    Plugin.prototype._bind = function () {
        var self = this;

        $(window).resize(function () {
            self._setSize();
        });
    };

    Plugin.prototype._getId = function () {
        if (!this.options.backgroundId) {
            this.options.backgroundId = defaults.backgroundId;
        }

        return this.options.backgroundId;
    };

    Plugin.prototype._createBackground = function () {
        this.$element = $('<div></div>').attr('id', this.id);

        this.$element.css({
            display: 'none',
            zIndex: this.options.zIndex,
            backgroundColor: '#ccc',
            left: 0,
            top: 0,
            /*IE*/
            filter: 'alpha(opacity=50)',
            /*FF*/
            opacity: '0.5',
            /*FF IE7*/
            position: 'fixed !important',
            /*IE6*/
            position: 'absolute'
        });

        this._setSize();
        $('body').append(this.$element);
    };

    Plugin.prototype._setSize = function () {
        this.$element.css({
            width: this.getScrollWidth(),
            height: this.getScrollHeight()
        });

        this.$element.css('_top', 'expression(eval(document.compatMode && document.compatMode==\'CSS1Compat\') ? documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 : document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);');
    };

    /* Public methods */

    Plugin.prototype.getScrollWidth = function () {
        return Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
    };

    Plugin.prototype.getScrollHeight = function () {
        return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    };

    Plugin.prototype.show = function () {
        if (this.$element.css('display') == 'none') {
            this.$element.show(arguments);
        }
    };

    Plugin.prototype.hide = function () {
        if (this.$element.css('display') != 'none') {
            this.$element.hide(arguments);
        }
    };

    /* Public methods end */

    window[pluginName] = Plugin;
})(jQuery, window, document);