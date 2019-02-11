/**
 * @license devutility.js v20190211
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

var devutility = {
    url: {},
    select: {}
};

devutility.url.addParam = function (url, name, value) {
    if (url.indexOf('?') != -1) {
        url += '&';
    } else {
        url += '?';
    }

    url += name + '=' + value;
    return url;
};

devutility.select.setByText = function (select, text) {
    var optionCount = select.options.length;

    for (var i = 0; i < optionCount; i++) {
        if (text == select.options[i].text) {
            select.options[i].selected = true;
            break;
        }
    }
};