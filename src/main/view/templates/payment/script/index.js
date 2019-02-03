var page = {
    url_list: '/payment/list',
    url_detail: '/user/detail',
    $list: $('#div-list'),
    vueHelper: null
};

page.init = function () {
    selector.init();
    this.bind();
};

page.bind = function () {
    this.bind_list();
};

page.bind_list = function () {
    $('#pagination').pagination({
        buttonClass: 'page-item',
        buttonAClass: 'page-link',
        paginationClass: 'pagination justify-content-center',
        onPageClick: function (pageIndex) {
            vueHelper.changePage(pageIndex);
        }
    });

    vueHelper = this.$list.vueHelper({
        url: this.url_list,
        onReload: function (data) {
            $('#pagination').data('pagination').changeTotalRecords(data.count);
        }
    });
};

page.init();