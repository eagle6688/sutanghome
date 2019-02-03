var page = {
    url_list: '/user/list',
    url_detail: '/user/detail',
    url_delete: '/user/delete',
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
        dataForm.set(result.data);
        page.formModal.edit();
    });
};

page.delete = function (id) {
    var url = devutility.url.addParam(page.url_delete, 'id', id);

    if (confirm('您确定要删除该用户吗？')) {
        $.getJSON(url, function (result) {
            alert(result.message);

            if (result.succeeded) {
                vueHelper.reload();
            }
        });
    }
};

page.init();