var page = {
    url_add: '/transfer/add',
    url_edit: '/transfer/edit',
    url_list: '/transfer/list',
    url_pattern_detail: '/transfer/detail?id={0}',
    vueHelper: null,
    addFormModal: null,
    editFormModal: null,
    $list: $('#div-list')
};

page.init = function () {
    selector.init();

    var afterSave = function (result, modal) {
        if (result.succeeded) {
            page.vueHelper.reload();
        }
    };

    var addFormModalOptions = $.extend({}, common.formModalOptions, {
        saveUrl: page.url_add,
        beforeShow: function () {
            $('#select-user').val(common.userId);
            $('#select-toUserId').val(common.userId);
        },
        afterSave: afterSave
    });

    page.addFormModal = new FormModal(addFormModalOptions);

    var editFormModalOptions = $.extend({}, common.formModalOptions, {
        loadFormDataBeforeShow: true,
        formDataUrlFormat: page.url_pattern_detail,
        formDataName: 'data',
        afterSetFormData: function (result, modal) {
            devutility.select.setByText($('#select-medium')[0], result.data.medium);

            if (result.data.time) {
                $('#txt-time').val(result.data.time.substr(0, 10));
            }
        },
        saveUrl: page.url_edit,
        afterSave: afterSave
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

page.init();