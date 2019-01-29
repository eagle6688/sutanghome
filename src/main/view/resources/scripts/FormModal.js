/**
 * @license form-modal.js v20181106
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'FormModal';

    var defaults = {
        modalSelector: null,
        formSelector: null,
        saveBtnSelector: null,
        url: null,
        saveClick: function () {},
        savedEvent: function (data, modal) {}
    };

    function Plugin(options) {
        this.options = $.extend({}, defaults, options);
        this._init();
    }

    Plugin.prototype.constructor = Plugin;

    Plugin.prototype._init = function () {
        if (!this._verify()) {
            return;
        }

        this.$modal = $(this.options.modalSelector);
        this.$form = $(this.options.formSelector);
        this.$saveBtn = $(this.options.saveBtnSelector);
        this._bind();
    };

    Plugin.prototype._verify = function () {
        if (!this.options.modalSelector) {
            console.error('"modalSelector" cannot be null!');
            return false;
        }

        if (!this.options.formSelector) {
            console.error('"formSelector" cannot be null!');
            return false;
        }

        if (!this.options.saveBtnSelector) {
            console.error('"saveBtnSelector" cannot be null!');
            return false;
        }

        if (!this.options.url) {
            var url = $(this.options.formSelector).attr('action');

            if (!url) {
                console.error('"url" cannot be null!');
                return false;
            }

            this.options.url = url;
        }

        return true;
    };

    Plugin.prototype._bind = function () {
        this._bindSaveBtn();
    };

    Plugin.prototype._bindSaveBtn = function () {
        var self = this;

        this.$saveBtn.click(function () {
            if (self.options.saveClick) {
                self.options.saveClick();
            }

            self._ajax(self.$form.serialize());
        });
    };

    Plugin.prototype._ajax = function (data) {
        var self = this;

        $.post(this.options.url, data, function (data) {
            self._savedEvent(data);
        });
    };

    Plugin.prototype._savedEvent = function (data) {
        var self = this;

        if (this.options.savedEvent) {
            this.options.savedEvent(data, self);
        }
    };

    Plugin.prototype.show = function () {
        this.$modal.modal('show');
    };

    Plugin.prototype.hide = function () {
        this.$modal.modal('hide');
    };

    Plugin.prototype.setUrl = function (url) {
        if (url) {
            this.options.url = url;
        }
    };

    window[pluginName] = Plugin;
})(jQuery, window, document);