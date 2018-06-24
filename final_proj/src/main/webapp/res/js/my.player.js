function my_player_player() {
    $.ajax({
        url: "player",
        success: my_player_success_player
    });
}

function my_player_success_player(jobj) {
    $("#my-player-container").html("").append(jobj.tags);
    $("button.jp-play").on("click",function(e){$(e.currentTarget).toggleClass("glyphicon-play").toggleClass("glyphicon-pause")});
    $("button.jp-stop").on("click",function(e){
        $("button.jp-play").addClass("glyphicon-play glyphicon-pause").removeClass("glyphicon-play");
    });
}


function my_player_load() {
    $("#jquery_jplayer_1").jPlayer({
        ready: function (event) {},
		swfPath: "${rootPath}/res/jplayer/js",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: false,
		toggleDuration: false
	});
}

function my_player_playmusic() {
    $("#jquery_jplayer_1").jPlayer("setMedia", {
        mp3: "member/music?musicno=" + $(this).attr("musicno")
    }).jPlayer("play");
    $("button.jp-play").removeClass("glyphicon-play glyphicon-pause").addClass("glyphicon-pause");
}
