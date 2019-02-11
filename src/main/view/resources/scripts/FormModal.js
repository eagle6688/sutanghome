/**
 * @license FormModal.js v20190211
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'FormModal';

    var defaults = {
        modalSelector: null,
        formSelector: null,
        saveBtnSelector: null,
        addUrl: null,
        editUrl: null,
        saveClick: function () {},
        requested: function (data) {}
    };

    var operationType = {
        add: 1,
        edit: 2
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
        this.operationType = operationType.add;
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

        if (!this.options.addUrl) {
            console.error('"addUrl" cannot be null!');
            return false;
        }

        if (!this.options.editUrl) {
            console.error('"editUrl" cannot be null!');
            return false;
        }

        if (!this.options.saveBtnSelector) {
            console.error('"saveBtnSelector" cannot be null!');
            return false;
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

            var url = self._getUrl();
            var data = self.$form.serialize();
            self._ajax(url, data);
        });
    };

    Plugin.prototype._getUrl = function () {
        if (this.operationType == operationType.add) {
            return this.options.addUrl;
        }

        return this.options.editUrl;
    };

    Plugin.prototype._ajax = function (url, data) {
        var self = this;

        $.post(url, data, function (data) {
            if (self.options.requested) {
                self.options.requested(data, self);
            }
        });
    };

    Plugin.prototype.show = function () {
        this.$modal.modal('show');
    };

    Plugin.prototype.hide = function () {
        this.$modal.modal('hide');
        this.clear();
    };

    Plugin.prototype.setOperationType = function (type) {
        this.operationType = type;
    };

    Plugin.prototype.add = function () {
        if (this.operationType != operationType.add) {
            this.clear();
        }

        this.setOperationType(operationType.add);
        this.show();
    };

    Plugin.prototype.edit = function () {
        if (this.operationType != operationType.edit) {
            this.clear();
        }

        this.setOperationType(operationType.edit);
        this.show();
    };

    Plugin.prototype.clear = function () {
        this.$form.find(':input').each(function () {
            var $this = $(this);

            if (!($this.attr('readonly') || $this.attr('disabled'))) {
                $this.val('');
            }
        });
    };

    window[pluginName] = Plugin;
})(jQuery, window, document);