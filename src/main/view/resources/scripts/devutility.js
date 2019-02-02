/**
 * @license devutility.js v20190202
 * (c) Aldwin. https://github.com/eagle6688
 * License: MIT
 */

var devutility = {
    url: {}
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