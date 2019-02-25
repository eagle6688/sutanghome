var page = {
    url_add: '/user/add',
    url_edit: '/user/edit',
    url_list: '/user/list',
    url_delete: '/user/delete',
    url_pattern_detail: '/user/detail?id={0}',
    $list: $('#div-list'),
    vueHelper: null,
    addFormModal: null,
    editFormModal: null
};

page.init = function () {
    selector.init();

    var addFormModalOptions = $.extend({}, common.formModalOptions, {
        saveUrl: page.url_add,
        afterSave: function (result, modal) {
            if (result.succeeded) {
                page.vueHelper.reload();
            }
        }
    });

    page.addFormModal = new FormModal(addFormModalOptions);

    var editFormModalOptions = $.extend({}, common.formModalOptions, {
        loadFormDataBeforeShow: true,
        formDataUrlFormat: page.url_pattern_detail,
        formDataName: 'data',
        saveUrl: page.url_edit,
        afterSave: function (result, modal) {
            if (result.succeeded) {
                page.vueHelper.reload();
            }
        }
    });

    page.editFormModal = new FormModal(editFormModalOptions);
    page.bind();
};

page.bind = function () {
    page.vueHelper = common.bind_list(page.$list, page.url_list);
    page.bind_addBtn();
};

page.bind_addBtn = function () {
    $('#btn-add').click(function () {
        page.addFormModal.show();
    });
};

page.edit = function (id) {
    page.editFormModal.show(id);
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