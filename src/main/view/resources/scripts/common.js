var common = (function () {
    var common = {
        signOutUrl: '/sign/out'
    };

    common.init = function () {
        common.init_ajax();
        common.init_navigator_scrollbar();
        common.init_navigator_sublist();
        common.init_mobile();
        common.init_dropdown();
        common.init_sign_out();
    };

    common.init_ajax = function () {
        $.ajaxSetup({
            global: true,
            cache: false,
            error: function (xhr, status, error) {
                alert('系统错误，请联系管理员！');
            }
        });
    };

    common.init_navigator_scrollbar = function () {
        var scrollbar = $('#div-scrollbar');

        if (scrollbar[0]) {
            new PerfectScrollbar(scrollbar[0]);
        }
    };

    common.init_navigator_sublist = function () {
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

    common.init_mobile = function () {
        $('.hamburger').on('click', function () {
            $(this).toggleClass('is-active');
            $('.navbar-mobile').slideToggle('500');
        });
    };

    common.init_dropdown = function () {
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

    common.init_sign_out = function () {
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

    return {
        init: common.init,
        bind_list: common.bind_list
    };
})();

$(document).ready(function () {
    common.init();
});