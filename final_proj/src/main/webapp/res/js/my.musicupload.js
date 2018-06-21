function my_musicupload_load(){
    $.ajax({
        url:"mgmt/musicselectitem",
        cache:false,
        type:"GET",
        success:my_musicupload_success_load
    });
    $( function() {
        $( "#my-upload-music-launch" ).datepicker();
        $( "#my-upload-music-music" ).on("change", my_musicupload_success_music_select);
        $( "#my-upload-music-album" ).on("change", my_musicupload_change_img);
        $(".my-upload-submit-button").on("click", my_musicupload_upload);
    } );
}

function my_musicupload_success_load(jobj){
    $("#my-upload-music-album option:not([disabled])").remove()
    $.each(jobj.album,function(idx, album){
        var template = '<option value="'+album.albumno+'" thumb="'+album.thumb+'">'+album.albumname+'</option>';
        $("#my-upload-music-album").append(template);
    });

    $("#my-upload-music-genre option:not([disabled])").remove()
    $.each(jobj.genre,function(idx, genre){
        var template = '<option value="'+genre+'">'+genre+'</option>';
        $("#my-upload-music-genre").append(template);
    });
}

function my_musicupload_change_img(e){
    $("#my-upload-album-img").attr("src","albumthumb?fname="+$("#my-upload-music-album option:selected").attr("thumb"))
}
/*function my_musicupload_picture_select() {
//    var formData = new FormData($("#upload-form")[0]);
    var formData = new FormData();
    formData.append("mp3", $(this)[0].files[0]);

    $.ajax({
        url: "mp3test",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        success: my_musicupload_success_picture_select
    });
}*/

function my_musicupload_success_music_select(e) {
    console.log("my-upload-music-music 파일 확장자 체크");

    var filename = $(e.currentTarget).val();

    if(filename.match(/(.mp3)$/)){
        console.log("my-upload-music-music에 파일 이름 걸기");

        var _filename = filename.split('\\');
        $("#my-upload-music-filename").text(_filename[_filename.length-1]);
    }else{
        $("#my-upload-music-filename").text("");
        $(e.currentTarget).val("");
        alert("mp3 파일만 업로드 가능합니다.");
    }
}

function my_musicupload_upload() {
    var name = $("#my-upload-music-name");
    var launch = $("#my-upload-music-launch");
    var music = $("#my-upload-music-music");
    var type = $(".my-upload-input-radio:checked");

    var bname = name.val().length > 0 ? true : false;
    var blaunch = launch.val().length > 0 ? true : false;
    var bmusic = music.val() != "" ? true : false;
    var btype = type.length > 0 ? true : false;


    if (bname && blaunch && bmusic && btype) {
        var formData = new FormData($("form")[0]);
        formData.musicmusic = music[0].files[0];

        $.ajax({
            url: "mgmt/musicupload",
            type: "POST",
            cache: false,
            processData: false,
            contentType: false,
            data: formData,
            success: my_musicupload_success_musicupload
        });
    } else {
        console.log(bname);
        console.log(blaunch);
        console.log(bmusic);
        console.log(btype);
    }
}

function my_musicupload_success_musicupload(jobj) {
    if (jobj.state == RespState.success) {
        my_mgmt_musicmgmt();
    }
}
