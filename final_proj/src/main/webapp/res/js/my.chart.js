function my_chart_test() {
    var r = $("#my-chart-music-row-template").clone(true, true);
    r.find("#my-chart-cb").attr("name", "mno").attr("value", "kk2");

    $("#my-chart-music-container").html("").append(r.clone().show()).append(r.clone().show());
}

function my_chart_add_music(no, jobj) {
    var mRow = $("#my-chart-music-row-template").clone();
    mRow.find("#my-chart-cb").attr("value", jobj.no);
    mRow.find(".my-chart-no").text(no);
//    이미지 이름
//    mRow.children(".my-chart-img").attr("src", );
    mRow.find(".my-chart-name").text(jobj.name);
    mRow.find(".my-chart-artist").text(jobj.artist);
    mRow.find(".my-chart-album").text(jobj.album);
//    좋아요 여부
//    mRow.children(".my-chart-likebtn")
    mRow.find(".my-chart-likecnt").text(jobj.likecnt);

    $("#my-chart-music-container").append(mRow.show());
}


function my_chart_chart(target, page) {
    $.ajax({
        type: "POST",
        url: "chart",
        async: true,
        success: my_chart_success_chart
    });

    // 음원데이터를 내려받아 row채우기
    my_chart_getMusic(target, page);
}

function my_chart_success_chart(jobj) {
    $("#my-contents-container").html("").append(jobj.tags);
}


function my_chart_getMusic(target, page) {
    $.ajax({
        url: "getmusic",
        data: {
            type: target,
            pg: page
        },
        success: my_chart_success_getMusic
    });
}

function my_chart_success_getMusic(jobj) {
    var music = jobj.music;

    $(".my-chart-contents").html("");

    for (var i = 0; i < music.length; i++) {
        var musicrow = $(".my-chart-music-row").clone();

        musicrow.find(".my-chart-no").text(i + 1);
        musicrow.find(".my-chart-name").text(music[i].name);
        musicrow.find(".my-chart-artist").text(music[i].artist);
        musicrow.find(".my-chart-album").text(music[i].albumname);
        musicrow.find("img").css("width", "60px").css("height", "60px").attr("src", "albumpic?fname=" + music[i].thumb);
        musicrow.find(".my-chart-likecount").text(music[i].likecount);
        musicrow.find(".my-chart-listenbtn").attr("musicno", music[i].no).on("click", my_player_playmusic);
        
        musicrow.find(".my-chart-downbtn").attr("mno", music[i].no).on("click", my_chart_musicdown);

        $(".my-chart-contents").append(musicrow);
    }
}

function my_chart_musicdown() {
    if ($("#my-header-useridbtn") != null && $("#my-header-useridbtn") != undefined) {
        $(location).attr("href", "member/musicdown?musicno=" + $(this).attr("mno"));
        my_header_header();
    }
}