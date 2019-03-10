/**
 * devutility.js v20190309
 * @license: MIT (c) Aldwin Su. https://github.com/eagle6688
 */

(function (window, document, undefined) {
    var devutility = {
        url: {},
        date: {},
        html: {},
        array: {},
        string: {},
        select: {},
        options: {}
    };

    /* url */

    var url = {};

    url.addParam = function (url, name, value) {
        if (url.indexOf('?') != -1) {
            url += '&';
        } else {
            url += '?';
        }

        url += name + '=' + value;
        return url;
    };

    devutility.url = url;

    /* url end */

    /* date */

    var date = {};

    date.test = function (regExp, value) {
        if (value == null || value == undefined || value.length == 0) {
            return true;
        }

        if (typeof value == 'object') {
            return true;
        }

        if (typeof value == 'string') {
            return regExp.test(value);
        }

        return false;
    };

    date.extract = function (str, format, pattern) {
        var index = format.indexOf(pattern);

        if (index < 0 || str.length != format.length) {
            return null;
        }

        return str.substr(index, pattern.length);
    };

    date.extractToInt = function (str, format, pattern) {
        var value = date.extract(str, format, pattern);

        if (!value) {
            return -1;
        }

        return parseInt(value);
    };

    date.addPrefix = function (part, length) {
        var value = part.toString();
        var lengthSub = length - value.length;

        while (lengthSub > 0) {
            value = string.addPrefix(value, '0');
            lengthSub--;
        }

        return value;
    };

    date.serialize = {
        yyyy: function (_date) {
            return _date.getFullYear().toString();
        },
        MM: function (_date) {
            return date.addPrefix(_date.getMonth() + 1, 2);
        },
        dd: function (_date) {
            return date.addPrefix(_date.getDate(), 2);
        },
        HH: function (_date) {
            return date.addPrefix(_date.getHours(), 2);
        },
        mm: function (_date) {
            return date.addPrefix(_date.getMinutes(), 2);
        },
        ss: function (_date) {
            return date.addPrefix(_date.getSeconds(), 2);
        },
        ccc: function (_date) {
            return date.addPrefix(_date.getMilliseconds(), 3);
        }
    };

    date.deserialize = {
        year: {
            yyyy: function (str, format) {
                return date.extractToInt(str, format, 'yyyy');
            }
        },
        month: {
            MM: function (str, format) {
                return date.extractToInt(str, format, 'MM') - 1;
            }
        },
        day: {
            dd: function (str, format) {
                return date.extractToInt(str, format, 'dd');
            }
        },
        hour: {
            HH: function (str, format) {
                return date.extractToInt(str, format, 'HH');
            }
        },
        minute: {
            mm: function (str, format) {
                return date.extractToInt(str, format, 'mm');
            }
        },
        second: {
            ss: function (str, format) {
                return date.extractToInt(str, format, 'ss');
            }
        },
        millisecond: {
            ccc: function (str, format) {
                return date.extractToInt(str, format, 'ccc');
            }
        }
    };

    date.format = function (_date, format) {
        var value = format;

        for (var pattern in date.serialize) {
            var regExp = new RegExp(pattern, 'g');
            var patternValue = date.serialize[pattern].call(this, _date);
            value = value.replace(regExp, patternValue);
        }

        return value;
    };

    date.parse = function (str, format) {
        var params = {
            year: 1970,
            month: 1,
            day: 1,
            hour: 0,
            minute: 0,
            second: 0,
            millisecond: 0
        };

        for (var param in date.deserialize) {
            var value = 0;

            for (var pattern in date.deserialize[param]) {
                if (string.contain(format, pattern)) {
                    value = date.deserialize[param][pattern].call(this, str, format);
                    break;
                }
            }

            if (value < 0) {
                console.error('Invalid str or format!');
                return null;
            }

            if (value > 0) {
                params[param] = value;
            }
        }

        return new Date(params.year, params.month, params.day, params.hour, params.minute, params.second, params.millisecond);
    };

    date.add = function (_date, milliseconds) {
        var time = _date.getTime();
        return new Date(time + milliseconds);
    };

    date.addHour = function (_date, hour) {
        var milliseconds = hour * 60 * 60 * 1000;
        return date.add(_date, milliseconds);
    };

    date.addDay = function (_date, day) {
        var milliseconds = day * 24 * 60 * 60 * 1000;
        return date.add(_date, milliseconds);
    };

    date.addMonth = function (_date, month) {
        var params = {
            year: _date.getFullYear(),
            month: _date.getMonth() + month,
            day: _date.getDate(),
            hour: _date.getHours(),
            minute: _date.getMinutes(),
            second: _date.getSeconds(),
            millisecond: _date.getMilliseconds()
        };

        var years = Math.floor(month / 12);

        if (years > 0) {
            params.year = params.year + years;
        }

        var months = month % 12;
        params.month = params.month + months;
        return new Date(params.year, params.month, params.day, params.hour, params.minute, params.second, params.millisecond);
    };

    date.addYear = function (_date, year) {
        var params = {
            year: _date.getFullYear() + year,
            month: _date.getMonth(),
            day: _date.getDate(),
            hour: _date.getHours(),
            minute: _date.getMinutes(),
            second: _date.getSeconds(),
            millisecond: _date.getMilliseconds()
        };

        return new Date(params.year, params.month, params.day, params.hour, params.minute, params.second, params.millisecond);
    };

    date.isLeapYear = function (year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }

        return false;
    };

    devutility.date = date;

    /* date end */

    /* html */

    var html = {};

    html.pageWidth = function () {
        if (!document.body || !document.documentElement) {
            return 0;
        }

        return Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
    };

    html.pageHeight = function () {
        if (!document.body || !document.documentElement) {
            return 0;
        }

        return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    };

    devutility.html = html;

    /* html end */

    /* array */

    var array = {};

    array.indexOf = function (_array, value) {
        for (var i = 0; i < _array.length; i++) {
            if (_array[i] == value) {
                return i;
            }
        }

        return -1;
    };

    array.contain = function (_array, value) {
        return array.indexOf(_array, value) > -1;
    };

    devutility.array = array;

    /* array end */

    /* string */

    var string = {};

    string.contain = function (str, value) {
        return str.indexOf(value) > -1;
    };

    string.addPrefix = function (str, prefix, count) {
        if (!count) {
            count = 1;
        }

        var value = str;

        for (var i = 0; i < count; i++) {
            value = prefix + value;
        }

        return value;
    };

    devutility.string = string;

    /* string end */

    /* select */

    var select = {};

    select.setByText = function (select, text) {
        var optionCount = select.options.length;

        for (var i = 0; i < optionCount; i++) {
            if (text == select.options[i].text) {
                select.options[i].selected = true;
                break;
            }
        }
    };

    devutility.select = select;

    /* select end */

    /* options */

    var options = {};

    options.isKVChanged = function (_options, name, value) {
        return _options.hasOwnProperty(name) && _options[name] !== value;
    };

    options.isChanged = function (_options, newOptions) {
        for (var name in newOptions) {
            if (options.isKVChanged(_options, name, newOptions[name])) {
                return true;
            }
        }

        return false;
    };

    devutility.options = options;

    /* options end */

    window.devutility = devutility;
})(window, document);