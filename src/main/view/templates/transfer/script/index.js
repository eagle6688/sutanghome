var page = {
    url_list: '/transfer/list',
    url_detail: '/transfer/detail',
    $list: $('#div-list'),
    vueHelper: null,
    formModal: null
};

page.init = function () {
    selector.init();
    this.formModal = dataForm.init();
    this.bind();
};

page.bind = function () {
    this.bind_list();
    this.bind_addBtn();
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

page.bind_addBtn = function () {
    $('#btn-add').click(function () {
        page.formModal.add();
    });
};

page.edit = function (id) {
    var url = devutility.url.addParam(page.url_detail, 'id', id);

    $.getJSON(url, function (result) {
        page.formModal.edit();
        dataForm.set(result.data);
    });
};

page.init();