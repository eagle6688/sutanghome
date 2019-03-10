/**
 * Background.js v20190310
 * dependency: jQuery.js, devutility.js
 * @license: License: MIT (c) Aldwin Su. https://github.com/eagle6688
 */

(function ($, window, document, undefined) {
    var pluginName = 'Background';

    var defaults = {
        id: 'background', //Id for background.
        zIndex: 2000 //zIndex for background.
    };

    function Plugin(options) {
        this.options = $.extend({}, defaults, options);
        this.$element = this._get();
    }

    Plugin.prototype.constructor = Plugin;

    /* methods */

    Plugin.prototype._get = function () {
        var $background = $(document.getElementById(this.options.id));

        if ($background.length > 0) {
            return $background;
        }

        $background = $('<div></div>').attr('id', this.options.id).css({
            display: 'none',
            zIndex: this.options.zIndex,
            background: '#ccc',
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
            filter: 'alpha(opacity=50)',
            opacity: 0.5,
            position: 'fixed'
        });

        $('body').append($background);
        return $background;
    };

    /* methods end */

    /* Public methods */

    Plugin.prototype.show = function () {
        this.$element.show();
    };

    Plugin.prototype.hide = function () {
        this.$element.hide();
    };

    /* Public methods end */

    window[pluginName] = Plugin;
})(jQuery, window, document);