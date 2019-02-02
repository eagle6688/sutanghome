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
    this.bind();
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
                alert(data.message);
                formModal.hide();
                vueHelper.reload();
                return;
            }

            alert(data.message);
        }
    });
};

page.bind = function () {
    page.bind_list();
    page.bind_addBtn();
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

    vueHelper = $('#div-list').vueHelper({
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

page.init();