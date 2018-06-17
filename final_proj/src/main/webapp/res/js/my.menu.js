function my_menu_menu() {
    $.ajax({
        type: "POST",
        url: "menu",
        success: my_menu_success_menu
    });
}

function my_menu_success_menu(jobj) {
    $("#my-menu-container").html("").append(jobj.tags);
}

function my_menu_chart() {
    my_submenu_submenu("chartsubmenu");
}

function my_menu_lastest() {
    my_submenu_submenu("lastestsubmenu");
}

function my_menu_genre() {
    my_submenu_submenu("genresubmenu");
}

function my_menu_mymenu() {
    my_submenu_submenu("mysubmenu");
}

function my_menu_notice() {
    my_submenu_submenu("noticesubmenu");
}
