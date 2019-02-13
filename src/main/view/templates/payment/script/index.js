var page = {
    url_list: '/payment/list',
    url_detail: '/user/detail',
    $list: $('#div-list'),
    vueHelper: null
};

page.init = function () {
    selector.init();
    page.bind();
};

page.bind = function () {
    page.vueHelper = common.bind_list(page.$list, page.url_list);
};

page.init();