function my_header_home() {
	$("#my-menu-container").show();
	$("#my-submenu-container").html("");
    $("#my-contents-container").html("");
    
    my_submenu_chartsubmenu();
    my_chart_chart();
}

function my_header_success_home(json) {
//    var jobj = $.parseJSON(json);
    console.log(json.data);
}

function my_header_voucher() {

}

function my_header_login() {
//    $("#my-header-right-container").html($("<div class='my-header-logout'>logout</div>").on("click", my_header_logout));
    
    $("#my-header-right-container").html("");
}

function my_header_register() {
    $.ajax({
        url: 'reg',
        success: my_header_success_register
    });
}


function my_header_success_register(jobj) {
	$("#my-menu-container").html("");
    $("#my-submenu-container").html("");
    $("#my-contents-container").html(jobj.tags);
}

function my_header_logout() {
    console.log("kk");
}
