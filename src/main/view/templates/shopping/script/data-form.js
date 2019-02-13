var dataForm = {
    url_add: '/shopping/add',
    url_edit: '/shopping/edit'
};

dataForm.init = function () {
    return new FormModal({
        modalSelector: '#div-modal',
        formSelector: '#data-form',
        saveBtnSelector: '#div-modal-save',
        addUrl: this.url_add,
        editUrl: this.url_edit,
        requested: function (data, formModal) {
            alert(data.message);

            if (data.succeeded) {
                formModal.hide();
                page.vueHelper.reload();
            }
        }
    });
};

dataForm.set = function (model) {
    $('#txt-id').val(model.id);
    devutility.select.setByText($('#select-channel')[0], model.channel);
    devutility.select.setByText($('#select-medium')[0], model.medium);
    $('#txt-time').val(model.time.substr(0, 10));
    $('#txt-cost').val(model.cost);
    $('#txt-description').val(model.description);
};