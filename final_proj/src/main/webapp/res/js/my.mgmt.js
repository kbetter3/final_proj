// 가수/그룹 관리페이지 로드
function my_mgmt_artistmgmt() {
    $.ajax({
        url: "artistmgmt",
        success: my_mgmt_success_artistmgmt
    });
}

function my_mgmt_success_artistmgmt(jobj) {
    if (jobj.state == RespState.tags) {
        $("#my-contents-container").html("").append(jobj.tags);
        my_mgmt_artistcont();
    } else {
        console.log("권한이 없습니다.");
    }
}

// 가수/그룹 컨텐츠 불러오기
function my_mgmt_artistcont() {
    $.ajax({
        url: "artistcont",
        success: my_mgmt_success_artistcont
    });
}

function my_mgmt_success_artistcont(jobj) {
    console.log("로드되었음");
}


function my_mgmt_artistupload() {
    $.ajax({
        type: "GET",
        url: "mgmt/artistupload",
        success: my_mgmt_success_artisupload
    });
}

function my_mgmt_success_artisupload(jobj) {
    $("#my-contents-container").html("").append(jobj.tags);
}