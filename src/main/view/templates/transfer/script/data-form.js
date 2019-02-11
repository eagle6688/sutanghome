var dataForm = {
    url_add: '/transfer/add',
    url_edit: '/transfer/edit'
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
                vueHelper.reload();
            }
        }
    });
};

dataForm.set = function (model) {
    $('#txt-id').val(model.id);
    $('#select-toUserId').val(model.toUserId);
    devutility.select.setByText($('#select-medium')[0], model.medium);
    $('#txt-time').val(model.time.substr(0, 10));
    $('#txt-cost').val(model.cost);
    $('#txt-description').val(model.description);
};