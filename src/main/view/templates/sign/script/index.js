var page = {
    signUrl: '/sign/in',
    locker: false,
    $form: null,
    $signBtn: null
};

page.init = function () {
    this.$signBtn = $('#btn-sign');
    this.$form = this.$signBtn.parent('form');
    page.bind();
};

page.bind = function () {
    page.bind_btn_sign();
};

page.bind_btn_sign = function () {
    this.$signBtn.click(function () {
        if (page.locker) {
            return;
        }

        page.locker = true;
        page.sign();
    });
};

page.sign = function () {
    $.ajax({
        url: page.signUrl,
        type: 'POST',
        data: page.$form.serialize(),
        dataType: 'json',
        error: function () {
            page.locker = false;
        },
        success: function (data) {
            page.locker = false;
            alert(data.message);

            if (data.succeeded) {
                location.href = data.data;
            }
        }
    });
};

page.init();