var page = {
    url_list: '/transfer/list',
    url_detail: '/transfer/detail',
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
        page.formModal.edit();
        dataForm.set(result.data);
    });
};

page.init();