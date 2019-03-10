/**
 * jquery.vue.helper.js v20190309
 * dependency: jQuery.js, vue.js, devutility.js
 * @license: MIT (c) Aldwin Su. https://github.com/eagle6688
 */

(function ($, window, document, undefined) {
    var pluginName = 'vueHelper';

    var defaults = {
        url: '', //Base url for request data.
        pageIndex: 1, //Page index parameter in request url.
        pageSize: 10, //Page size parameter in request url.
        autoLoad: true, //Plugin would automatic request data when initializing.
        ajaxOptions: {
            type: 'GET',
            cache: false,
            dataType: 'json',
        }, //Jquery ajax options for request backend data.
        vueOptions: {
            data: {
                count: 0,
                data: []
            }
        }, //Initial options for Vue object.
        beforeRequestData: function () {}, //Event triggered before request data, for example display loading element.
        beforeLoadData: function (data) {}, //Event triggered before set view model but after requested data.
        afterLoadData: function (data) {} //Event triggered after set view model.
    };

    function Plugin(element, options) {
        this.$element = $(element);
        this.options = $.extend(true, {}, defaults, options);
        this.vueOptions = this.options.vueOptions;
        this._init();
    }

    Plugin.prototype.constructor = Plugin;

    /* Init */

    Plugin.prototype._init = function () {
        if (!this._verify()) {
            return;
        }

        this._initVue();
        this._initData();
    };

    Plugin.prototype._verify = function () {
        if (!this.$element.attr('id')) {
            console.error('Invalid value of attribute "id"!');
            return false;
        }

        return true;
    };

    Plugin.prototype._initVue = function () {
        this.selector = '#' + this.$element.attr('id');

        if (!this.options.vueOptions.hasOwnProperty('el')) {
            this.options.vueOptions.el = this.selector;
        }

        this.vue = new Vue(this.options.vueOptions);
    };

    Plugin.prototype._initData = function () {
        if (this.options.autoLoad) {
            this._load();
        }
    };

    /* Init end */

    /* Methods */

    Plugin.prototype._load = function () {
        this._beforeRequestData();
        this._ajax();
    };

    Plugin.prototype._ajax = function () {
        var self = this;
        var options = this.options.ajaxOptions;
        options.url = this._ajaxUrl();

        options.success = function (data) {
            self._ajaxSuccess(data);
        };

        $.ajax(options);
    };

    Plugin.prototype._ajaxUrl = function () {
        var url = this.options.url;
        url = devutility.url.addParam(url, 'pageIndex', this.options.pageIndex);
        url = devutility.url.addParam(url, 'pageSize', this.options.pageSize);
        url = devutility.url.addParam(url, 'skip', (this.options.pageIndex - 1) * this.options.pageSize);
        return url;
    };

    Plugin.prototype._ajaxSuccess = function (data) {
        this._beforeLoadData(data);
        this._setViewModel(data);
        this._afterLoadData(data);
    };

    Plugin.prototype._setViewModel = function (data) {
        this.vue.count = data.count;
        this.vue.data = data.data;
    };

    /* Methods end */

    /* Events */

    Plugin.prototype._beforeRequestData = function () {
        if (this.options.beforeRequestData) {
            this.options.beforeRequestData();
        }
    };

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

    /* Events end */

    /* Public methods */

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
            var obj = new Object();

            for (var index in vueObject) {
                var item = restore(vueObject[index]);
                obj[index] = item;
            }

            return obj;
        }

        return result;
    };

    Plugin.prototype.getOption = function (name) {
        return this.options[name];
    };

    Plugin.prototype.reload = function (options) {
        this.options = $.extend(true, {}, this.options, options);
        this._load();
    };

    Plugin.prototype.changePageIndex = function (pageIndex) {
        this.reload({
            pageIndex: pageIndex
        });
    };

    /* Public methods end */

    $[pluginName] = {
        restore: restore
    };

    $.fn[pluginName] = function (options) {
        return new Plugin(this, options);
    };
})(jQuery, window, document);