function my_player_player() {
    $.ajax({
        url: "player",
        success: my_player_success_player
    });
}

function my_player_success_player(jobj) {
    $("#my-player-container").html("").append(jobj.tags);
}