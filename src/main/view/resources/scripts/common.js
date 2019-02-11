(function () {
    var common = {
        signOutUrl: '/sign/out'
    };

    common.init = function () {
        this.init_ajax();
        this.init_navigator_scrollbar();
        this.init_navigator_sublist();
        this.init_dropdown();
        this.init_sign_out();
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

    $(document).ready(function () {
        common.init();
    });
})();