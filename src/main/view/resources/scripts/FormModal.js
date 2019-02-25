/**
 * @license FormModal.js v20190223
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

(function ($, window, document, undefined) {
    var pluginName = 'FormModal';

    var defaults = {
        modalSelector: null, //Selector for modal.
        formSelector: null, //Selector for form.
        saveBtnSelector: null, //Selector for save button.
        loadFormDataBeforeShow: false, //Whether load form data before modal shows.
        formDataUrlFormat: null, //Url format of request form data, for example http://www.test.com?p1={0}&p2={1}.
        formDataName: '', //Object name of form data response from formDataUrl, for example 'name1.name2.name3'.
        saveUrl: null, //Save url for form data.
        beforeShow: function (modal) {}, //Event before show modal, for example display loading image.
        afterRequestFormData: function (result, modal) { //Event after request form data, FormModal does nothing if this method returns false, for example validating response from formDataUrl.
            return true;
        },
        saveClick: function (modal) {}, //Event while clicking save button.
        checkSaveResult: function (result, modal) { //Event for validating result data from save url, FormModal does nothing if validation failed.
            return true;
        },
        afterSave: function (result, modal) {} //Event after saved form data.
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

        this.$modal = $(this.options.modalSelector).clone();
        this.$form = this.$modal.find(this.options.formSelector);
        this.$saveBtn = this.$modal.find(this.options.saveBtnSelector);
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

        if (!this.options.saveUrl) {
            var saveUrl = $(this.options.formSelector).attr('action');

            if (!saveUrl) {
                console.error('"saveUrl" cannot be null!');
                return false;
            }

            this.options.saveUrl = saveUrl;
        }

        return true;
    };

    Plugin.prototype._bind = function () {
        var self = this;

        this.$saveBtn.click(function () {
            self._saveClick(self);
        });
    };

    /* Events start */

    Plugin.prototype._beforeShow = function (paramters, callback) {
        if (this.options.beforeShow) {
            this.options.beforeShow(this);
        }

        this._loadFormData(paramters, callback);
    };

    Plugin.prototype._afterRequestFormData = function (result, modal) {
        if (this.options.afterRequestFormData) {
            return this.options.afterRequestFormData(result, modal);
        }

        return true;
    };

    Plugin.prototype._saveClick = function () {
        var self = this;

        if (this.options.saveClick) {
            this.options.saveClick(this);
        }

        this._save(this.$form.serialize(), function (result) {
            if (!self._checkSaveResult(result)) {
                return;
            }

            self._afterSave(result);
        });
    };

    Plugin.prototype._checkSaveResult = function (result) {
        if (this.options.checkSaveResult) {
            return this.options.checkSaveResult(result, this);
        }

        return true;
    };

    Plugin.prototype._afterSave = function (result) {
        if (this.options.afterSave) {
            this.options.afterSave(result, this);
        }

        this.hide();
    };

    /* Events end */

    /**
     * Load form data.
     */
    Plugin.prototype._loadFormData = function (paramters, callback) {
        var self = this;
        var url = this._getFormDataUrl(paramters);

        if (!this.options.loadFormDataBeforeShow) {
            callback();
            return;
        }

        this._requestFormData(url, function (result) {
            if (!self._afterRequestFormData(result, self)) {
                return;
            }

            var data = self._getFormDataFromResult(result);
            self._setForm(data);
            callback();
        });
    };

    /**
     * Get form data url.
     */
    Plugin.prototype._getFormDataUrl = function (paramters) {
        var url = this.options.formDataUrlFormat;

        if (!paramters || paramters.length == 0) {
            return url;
        }

        for (var i = 0; i < paramters.length; i++) {
            var regExp = new RegExp('\\{' + i.toString() + '\\}', 'i');
            url = url.replace(regExp, paramters[i]);
        }

        return url;
    };

    /**
     * Request form data from url.
     */
    Plugin.prototype._requestFormData = function (url, callback) {
        $.getJSON(url, function (result) {
            callback(result);
        });
    };

    /**
     * Get form data from result.
     */
    Plugin.prototype._getFormDataFromResult = function (result) {
        if (!this.options.formDataName) {
            return result;
        }

        var array = this.options.formDataName.split('.');
        var data = result[array[0]];

        for (var i = 1; i < array.length; i++) {
            data = data[array[i]];
        }

        return data;
    };

    /**
     * Set form with data.
     */
    Plugin.prototype._setForm = function (data) {
        if (!data) {
            return;
        }

        this.$form.find(':input').each(function () {
            var $this = $(this);
            var name = $this.attr('name');

            if (name) {
                var value = data[name];

                if (value) {
                    $this.val(value);
                }
            }
        });
    };

    /**
     * Save form data.
     */
    Plugin.prototype._save = function (data, callback) {
        $.post(this.options.saveUrl, data, function (data) {
            callback(data);
        });
    };

    /* Public methods */

    Plugin.prototype.show = function () {
        var self = this;

        this._beforeShow(arguments, function () {
            self.$modal.modal('show');
        });
    };

    Plugin.prototype.hide = function () {
        this.$modal.modal('hide');
    };

    Plugin.prototype.clear = function () {
        this.$form.find(':input').each(function () {
            var $this = $(this);

            if (!($this.attr('readonly') || $this.attr('disabled'))) {
                $this.val('');
            }
        });
    };

    /* Public methods end */

    window[pluginName] = Plugin;
})(jQuery, window, document);