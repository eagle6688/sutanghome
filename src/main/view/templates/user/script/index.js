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
    page.formModal = dataForm.init();
    page.bind();
};

page.bind = function () {
    page.vueHelper = common.bind_list(page.$list, page.url_list);
    page.bind_addBtn();
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
                page.vueHelper.reload();
            }
        });
    }
};

page.init();