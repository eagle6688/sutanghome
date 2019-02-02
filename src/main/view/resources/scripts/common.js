(function () {
    var common = {};

    common.init = function () {
        this.init_navigator_scrollbar();
        this.init_navigator_sublist();
        common.init_ajax();
    };

    common.init_navigator_scrollbar = function () {
        var scrollbar = $('#div-scrollbar');

        if (scrollbar[0]) {
            new PerfectScrollbar(scrollbar[0]);
        }
    };

    common.init_navigator_sublist = function () {
        $('.js-arrow').each(function () {
            $(this).on('click', function (e) {
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
        });
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

    common.init();
})();