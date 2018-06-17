function my_submenu_submenu(target) {
    $.ajax({
        url: "submenu",
        data: {fname: target},
        success: my_submenu_success_submenu
    });
}

function my_submenu_success_submenu(jobj) {
    $("#my-submenu-container").html("").append(jobj.tags);
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
