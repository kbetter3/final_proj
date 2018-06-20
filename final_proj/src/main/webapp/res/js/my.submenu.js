function my_submenu_submenu(target) {
    $.ajax({
        url: "submenu",
        data: {fname: target},
        success: my_submenu_success_submenu
    });
}

function my_submenu_success_submenu(jobj) {
    if (jobj.state == RespState.tags) {
        $("#my-submenu-container").html("").append(jobj.tags);
    } else {
        console.log("로그인 전용 메뉴");
    }
}


// chartsubmenu item 선택시
function my_submenu_changeSubmenu(target, pg) {
    my_chart_chart($(this).attr("controller"), 1);
}


// 가수/그룹 서브메뉴 클릭시
function my_submenu_artistmgmt() {
    my_mgmt_artistmgmt();
}


// 관리자 서브메뉴
