function my_header_home() {
    $("#my-submenu-container").html("");
    $("#my-contents-container").html("");

    my_menu_menu();
    my_submenu_submenu("chartsubmenu");
}

function my_header_success_home(json) {
    console.log(json.data);
}


function my_header_header() {
    $.ajax({
        url: "header",
        success: my_header_success_header
    });
}

function my_header_success_header(jobj) {
    var member = jobj.member;

//    console.log(member);

    $("#my-header-right-container").html("").append(jobj.tags);
    if (member != null) {
        $("#my-header-useridbtn").text(member.uid);
        $("#my-header-voucher").text(member.expiredate);
        $("#my-header-downcnt").text(member.downcount);
    } else {
        $("#my-header-voucher").text("-/-");
        $("#my-header-downcnt").text("-/-");
    }
}


function my_header_voucher() {
    $.ajax({
        type: "GET",
        url: "voucher",
        success: my_header_success_voucher
    });
}

function my_header_success_voucher(jobj) {
    if (jobj.state == RespState.tags) {
        $("#my-menu-container").html("");
        $("#my-submenu-container").html("");
        $("#my-contents-container").html("").append(jobj.tags);
    } else {
        alert("로그인 전용 메뉴 입니다");
    }
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
    my_player_player();
    my_header_header();
}
