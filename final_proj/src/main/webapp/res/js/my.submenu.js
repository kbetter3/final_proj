/**
 * 
 */

function my_submenu_chartsubmenu() {
    console.log("서브메뉴 호출");
    $.ajax({
        type: "POST",
        url: "chartsubmenu",
        success: my_submenu_success_chartsubmenu
    });
}

function my_submenu_success_chartsubmenu(jobj) {
    console.log("서브메뉴 불러오기 성공");
    $("#my-submenu-container").append(jobj.tags);
}