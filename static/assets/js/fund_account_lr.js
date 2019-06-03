$(document).ready(
    function() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/trade/lender/account",
        success: function (res) {
            $("#avatar").src(res.avatar_url);
            $("#user_name").text(res.user_name);
            $("#bank_account").text(res.bank_account);
            $("#user_balance").text(res.user_balance);
        },
        error: function() {}

    })
}
);