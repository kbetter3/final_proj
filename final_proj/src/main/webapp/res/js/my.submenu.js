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


function my_submenu_getMusic() {
    my_chart_chart();

    $.ajax({
        url: "getmusic",
        data: {
            type: $(this).attr("controller"),
            page: 1
        },
        success: my_submenu_success_getMusic
    });
}

function my_submenu_success_getMusic(jobj) {
    console.log(jobj.music.length);
}


// 가수/그룹 서브메뉴 클릭시
function my_submenu_artistmgmt() {
    my_mgmt_artistmgmt();
}


// 관리자 서브메뉴