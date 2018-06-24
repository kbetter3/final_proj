function my_mgmt_myinfo() {
    $.ajax({
        url: "member/myinfo",
        success: my_mgmt_success_myinfo
    });
}

function my_mgmt_success_myinfo(jobj) {
    $("#my-contents-container").html("").append(jobj.tags);
}

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
        // console.log("권한이 없습니다.");
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
    var artist = jobj.artist;

    for (var i = 0; i < artist.length; i++) {
        var artistrow = $(".my-artistmgmt-artist-row-template").clone();
        artistrow.removeClass("my-artistmgmt-artist-row-template").addClass("my-artistmgmt-artist-row");

        artistrow.find(".my-artistmgmt-no").text(i + 1);
        artistrow.find("img").css("width", "60px").css("height", "60px").attr("src", "mgmt/artistpic?fname=" + artist[i].thumb);
        artistrow.find(".my-artistmgmt-name").text(artist[i].name);
        artistrow.find(".my-artistmgmt-member").text(artist[i].member);
        artistrow.find(".my-artistmgmt-activitytype").text(artist[i].activitytype);
        artistrow.find(".my-artistmgmt-agency").text(artist[i].agency);
        artistrow.find(".my-artistmgmt-debutdate").text(artist[i].debutdate);
        artistrow.find(".my-artistmgmt-likecount").text(artist[i].likecount);

        $(".my-artistmgmt-contents").append(artistrow);
    }
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
        //console.log("권한이 없습니다.");
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
    var album = jobj.album;

    //console.log("album : " + album.length);

    for (var i = 0; i < album.length; i++) {
        var albumrow = $(".my-albummgmt-album-row-template").clone();
        albumrow.removeClass("my-albummgmt-album-row-template").addClass("my-albummgmt-album-row");

        albumrow.find(".my-albummgmt-no").text(i + 1);
        albumrow.find("img").css("width", "60px").css("height", "60px").attr("src", "mgmt/albumpic?fname=" + album[i].thumb);
        albumrow.find(".my-albummgmt-name").text(album[i].name);
        albumrow.find(".my-albummgmt-member").text(album[i].member);
        albumrow.find(".my-albummgmt-genre").text(album[i].genre);
        albumrow.find(".my-albummgmt-agency").text(album[i].agency);
        albumrow.find(".my-albummgmt-releasedate").text(album[i].releasedate);
        albumrow.find(".my-albummgmt-likecount").text(album[i].likecount);

        $(".my-albummgmt-contents").append(albumrow);
    }
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
    var music = jobj.music;

    for (var i = 0; i < music.length; i++) {
        var musicrow = $(".my-musicmgmt-music-row-template").clone();
        musicrow.removeClass("my-musicmgmt-music-row-template").addClass("my-musicmgmt-music-row");

        musicrow.find(".my-musicmgmt-no").text(i + 1);

        musicrow.find(".my-musicmgmt-name").text(music[i].name);
        musicrow.find(".my-musicmgmt-artist").text(music[i].artist);
        $.ajax({
            url: "albumname",
            data: {albumno: music[i].albumno},
            success: function(jobj){
                musicrow.find(".my-musicmgmt-album").text(jobj.albumname);
                musicrow.find("img").css("width", "60px").css("height", "60px").attr("src", "mgmt/albumpic?fname=" + jobj.albumthumb);
            }
        });
        musicrow.find(".my-musicmgmt-likecount").text(music[i].likecount);

        $(".my-musicmgmt-contents").append(musicrow);
    }
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
