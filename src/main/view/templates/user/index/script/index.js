;

var page = {
    url_list: '/user/list',
    url_add: '/user/add',
    url_edit: '/user/edit',
    vueHelper: null,
    formModal: null
};

page.init = function () {
    selector.init();
    this.init_formModal();
};

page.init_formModal = function () {
    this.formModal = new FormModal({
        modalSelector: '#div-modal',
        formSelector: '#form-save',
        saveBtnSelector: '#div-modal-save',
        addUrl: this.url_add,
        editUrl: this.url_edit,
        requested: function (data, formModal) {
            if (data.successful) {
                alert('Save completely!');
                formModal.hide();
                vueHelper.reload();
                return;
            }

            alert(data.message);
        }
    });
};

//page.init();