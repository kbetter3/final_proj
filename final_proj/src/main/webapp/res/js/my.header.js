function my_header_home() {
    $("#my-submenu-container").html("");
    $("#my-contents-container").html("");

    my_chart_chart();
    my_menu_menu();
    my_submenu_submenu("chartsubmenu");
}

function my_header_success_home(json) {
    console.log(json.data);
}


function my_header_header() {
    console.log("헤더 ajax");
    $.ajax({
        url: "header",
        success: my_header_success_header
    });
}

function my_header_success_header(jobj) {
    $("#my-header-right-container").html("").append(jobj.tags);
    $("#my-header-useridbtn").text(jobj.uid);
}


function my_header_voucher() {
    $.ajax({
        type: "GET",
        url: "voucher",
        success: my_header_success_voucher
    });
}

function my_header_success_voucher(jobj) {
    $("#my-menu-container").html("");
    $("#my-submenu-container").html("");
    $("#my-contents-container").html("").append(jobj.tags);
}


function my_header_login() {
    $.ajax({
        type: "GET",
        url: "login",
        success: my_header_success_login,
        error: function(){console.log("error")}
    });
}

function my_header_success_login(jobj) {
    $("#my-menu-container").html("");
    $("#my-submenu-container").html("");
    $("#my-contents-container").html("").append(jobj.tags);
}


function my_header_register() {
    $.ajax({
        type: "GET",
        url: "register",
        success: my_header_success_register
    });
}

function my_header_success_register(jobj) {
    $("#my-menu-container").html("");
    $("#my-submenu-container").html("");
    $("#my-contents-container").html(jobj.tags);
}


function my_header_userid() {
    console.log("클릭했냐?");
    $.ajax({
        url: "myinfo",
        success: my_header_success_userid
    });
}

function my_header_success_userid(jobj) {
    $("#my-menu-container").html("");
    $("#my-submenu-container").html("").append(jobj.tags);
}


function my_header_logout() {
    $.ajax({
        type: "GET",
        url: "logout",
        success: my_header_success_logout
    });
}

function my_header_success_logout(jobj) {
    my_header_header();
}
