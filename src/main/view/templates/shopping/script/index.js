var page = {
    url_add: '/shopping/add',
    url_edit: '/shopping/edit',
    url_list: '/shopping/list',
    url_pattern_detail: '/shopping/detail?id={0}',
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
        afterSave: afterSave
    });

    page.addFormModal = new FormModal(addFormModalOptions);

    var editFormModalOptions = $.extend({}, common.formModalOptions, {
        loadFormDataBeforeShow: true,
        formDataUrlFormat: page.url_pattern_detail,
        formDataName: 'data',
        afterSetFormData: function (result, modal) {
            var model = result.data;
            devutility.select.setByText($('#select-channel')[0], model.channel);
            devutility.select.setByText($('#select-medium')[0], model.medium);

            if (model.time) {
                $('#txt-time').val(model.time.substr(0, 10));
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