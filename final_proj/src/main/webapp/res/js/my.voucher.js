function my_voucher_load() {
    $(".my-voucher-buy-button").on("click", my_voucher_buy);
}

function my_voucher_buy() {
    $.ajax({
        url: "member/voucher",
        type: "POST",
        data: { day: $(this).attr("day") },
        success: my_voucher_success_buy
    });
}

function my_voucher_success_buy() {
    console.log("구매성공");
}