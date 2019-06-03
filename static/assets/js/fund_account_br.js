$(document).ready(function() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/trade/borrower/account",
        success: function (res) {
            $("#avatar").src(res.avatar);
            $("#user_name").text(res.user_name);
            $("#score").text(res.score);
            $("#bank_account").text(res.bank_account);
            $("#user_balance").text(res.user_balance);
            $("#total_quato").text(res.total_quato);
            $("#unused_quato").text(res.unused_quato);
        },
        error: function() {}
    })
});
