var dataForm = {
    url_add: '/transfer/add',
    url_edit: '/user/edit'
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
    $('#txt-name').val(model.name);
    $('#txt-cellphone').val(model.cellphone);
};