var common = {
    signOutUrl: '/sign/out',
    userId: 0
};

common.init = function () {
    common.userId = ~~$('#hid-userId').val();
    common._init_ajax();
    common._init_navigator_scrollbar();
    common._init_navigator_sublist();
    common._init_mobile();
    common._init_dropdown();
    common._init_sign_out();
};

common._init_ajax = function () {
    $.ajaxSetup({
        global: true,
        cache: false,
        error: function (xhr, status, error) {
            common.loadingPopuper.hideAll();
            alert('系统错误，请联系管理员！');
        }
    });
};

common._init_navigator_scrollbar = function () {
    var scrollbar = $('#div-scrollbar');

    if (scrollbar[0]) {
        new PerfectScrollbar(scrollbar[0]);
    }
};

common._init_navigator_sublist = function () {
    $('.js-arrow').click(function (e) {
        e.preventDefault();
        var $this = $(this);
        var href = $this.attr('href');
        $this.find(".arrow").toggleClass("up");
        $this.toggleClass("open");
        $this.parent().find('.js-sub-list').slideToggle("250");

        if (href && href.indexOf('/') == 0 || href.indexOf('http') == 0) {
            location.href = href;
        }
    });

    $('#div-scrollbar a').each(function () {
        var link = $(this).attr('href');

        if (window.location.href.indexOf(link) > 0) {
            $(this).parent('li').addClass('active');
            $(this).parent('li').parent('ul[class*="js-sub-list"]').show();
        }
    });
};

common._init_mobile = function () {
    $('.hamburger').on('click', function () {
        $(this).toggleClass('is-active');
        $('.navbar-mobile').slideToggle('500');
    });
};

common._init_dropdown = function () {
    var $dropdowns = $('.js-item-menu');

    $dropdowns.click(function (e) {
        e.preventDefault();
        var $this = $(this);

        for (var i = 0; i < $dropdowns.length; i++) {
            if ($dropdowns[i] == $this[0]) {
                continue;
            }

            $($dropdowns[i]).removeClass('show-dropdown');
        }

        $this.toggleClass('show-dropdown');
    });

    $('.js-item-menu, .js-dropdown').click(function (event) {
        event.stopPropagation();
    });

    $('body,html').click(function () {
        $dropdowns.remove('show-dropdown');
    });
};

common._init_sign_out = function () {
    $('.account-dropdown__footer>a').click(function () {
        $.getJSON(common.signOutUrl, function (result) {
            if (result.succeeded) {
                location.href = result.data;
            } else {
                alert(result.message);
            }
        });
    });
};

common.bind_list = function ($list, listUrl) {
    var vueHelper = $list.vueHelper({
        url: listUrl,
        onReload: function (data) {
            $('#pagination').data('pagination').changeTotalRecords(data.count);
        }
    });

    $('#pagination').pagination({
        firstButtonName: '第一页',
        prevButtonName: '上一页',
        nextButtonName: '下一页',
        lastButtonName: '末尾页',
        buttonClass: 'page-item',
        buttonAClass: 'page-link',
        paginationClass: 'pagination justify-content-center',
        onPageClick: function (pageIndex) {
            vueHelper.changePage(pageIndex);
        }
    });

    return vueHelper;
};

/* Popuper and FormModal */

common.formModalOptions = {
    modalSelector: '#div-modal',
    formSelector: '#data-form',
    saveBtnSelector: '#div-modal-save',
    saveClick: common._saveClick,
    checkSaveResult: common._checkSaveResult
};

common.loadingPopuper = new Popuper({
    selector: '#img-loading'
});

common._saveClick = function (modal) {
    common.loadingPopuper.showAll();
};

common._checkSaveResult = function (result, modal) {
    common.loadingPopuper.hideAll();

    if (result.message) {
        alert(result.message);
    }

    if (result.succeeded) {
        return true;
    }

    return false;
};

/* Popuper and FormModal end */

common.init();