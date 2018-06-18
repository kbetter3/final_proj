// 가수/그룹 관리페이지 불러오기
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


// 앨범 관리페이지 불러오기
function my_mgmt_albummgmt() {
    $.ajax({
        url: "mgmt/albummgmt",
        success: my_mgmt_success_albummgmt
    });
}

function my_mgmt_success_albummgmt(jobj) {
    if (jobj.state == RespState.tags) {
        $("#my-contents-container").html("").append(jobj.tags);
        my_mgmt_albumcont();
    } else {
        console.log("권한이 없습니다.");
    }
}
// 앨범 컨텐츠 블러오기
function my_mgmt_albumcont() {
    $.ajax({
        url: "mgmt/albumcont",
        success: my_mgmt_success_albumcont
    });
}

function my_mgmt_success_albumcont(jobj) {
    console.log("로드되었음");
}

// 앨범 업로드페이지 불러오기
function my_mgmt_albumupload() {
    $.ajax({
        type: "GET",
        url: "mgmt/albumupload",
        success: my_mgmt_success_albumupload
    });
}
function my_mgmt_success_albumupload(jobj) {
    $("#my-contents-container").html("").append(jobj.tags);
}


// 음원 관리페이지 불러오기
function my_mgmt_musicmgmt() {
    $.ajax({
        url: "mgmt/musicmgmt",
        success: my_mgmt_success_musicmgmt
    });
}

function my_mgmt_success_musicmgmt(jobj) {
    if (jobj.state == RespState.tags) {
        $("#my-contents-container").html("").append(jobj.tags);
        my_mgmt_musiccont();
    }
}

// 음원 컨텐츠 불러오기
function my_mgmt_musiccont() {
    $.ajax({
        url: "mgmt/musiccont",
        success: my_mgmt_success_musiccont
    });
}

function my_mgmt_success_musiccont(jobj) {
    console.log("로드되었음")
}

// 음원 업로드페이지 불러오기
function my_mgmt_musicupload() {
    $.ajax({
        type: "GET",
        url: "mgmt/musicupload",
        success: my_mgmt_success_musicupload
    });
}

function my_mgmt_success_musicupload(jobj) {
    $("#my-contents-container").html("").append(jobj.tags);
}
