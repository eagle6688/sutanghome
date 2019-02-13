var page = {
    signUrl: '/sign/in',
    locker: false,
    $form: null,
    $signBtn: null
};

page.init = function () {
    page.$signBtn = $('#btn-sign');
    page.$form = page.$signBtn.parent('form');
    page.bind();
};

page.bind = function () {
    page.bind_btn_sign();
};

page.bind_btn_sign = function () {
    page.$signBtn.click(function () {
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

            if (data.succeeded) {
                location.href = data.data;
            } else {
                alert(data.message);
            }
        }
    });
};

page.init();