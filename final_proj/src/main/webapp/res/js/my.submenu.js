function my_submenu_submenu(target) {
    $.ajax({
        url: "submenu",
        data: {fname: target},
        async: true,
        success: my_submenu_success_submenu
    });
    
    var chartcontent;
    if (target == "chartsubmenu") {
        chartcontent = "chartrealtime";
    } else if (target == "lastestsubmenu") {
        chartcontent = "lastestdom";
    } else if (target == "genresubmenu") {
        chartcontent = "genreballad";
    }
    
    my_chart_chart(chartcontent, 1);
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
