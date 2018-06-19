function my_albumupload_load(){
    $.ajax({
        url:"mgmt/albumselectitem",
        cache:false,
        type:"GET",
        success:my_albumupload_success_load
    });
    
    $( function() {
        $( "#my-upload-album-launch" ).datepicker();
        $(".my-upload-submit-button").on("click", my_albumupload_upload);
    } );
}

function my_albumupload_success_load(jobj){
    $("#my-upload-album-artist option:not([disabled])").remove()
    $.each(jobj.artist,function(idx, artist){
        var template = '<option value="'+artist.artistno+'">'+artist.artistname+'</option>';
        $("#my-upload-album-artist").append(template);
    })

    $("#my-upload-album-genre option:not([disabled])").remove()
    $.each(jobj.genre,function(idx, genre){
        var template = '<option value="'+genre+'">'+genre+'</option>';
        $("#my-upload-album-genre").append(template);
    })
}

function my_albumupload_picture_select() {
//    var formData = new FormData($("#upload-form")[0]);
    var formData = new FormData();
    formData.append("pic", $(this)[0].files[0]);

    $.ajax({
        url: "pictest",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        success: my_albumupload_success_picture_select
    });
}

function my_albumupload_success_picture_select(jobj) {
    var fname = jobj.data;

    console.log("my-upload-album-img에 src 걸기");
    console.log(jobj.data);
    $("#my-upload-album-img").attr("src", "pictest2?fname=" + jobj.data);
}

function my_albumupload_upload() {
    var name = $("#my-upload-album-name");
    var artist = $("#my-upload-album-artist");
    var launch = $("#my-upload-album-launch");
    var genre = $("#my-upload-album-genre");
    var company = $("#my-upload-album-company");
    var thumb = $("#my-upload-album-img");

    var bname = name.val().length > 0 ? true : false;
    var bartist = artist.val()!=null ? true : false;
    var blaunch = launch.val().length > 0 ? true : false;
    var bthumb = thumb[0].files[0] != undefined ? true : false;



    if (bname && bartist && blaunch && bthumb) {
        var formData = new FormData($("form")[0]);

        $.ajax({
            url: "mgmt/albumupload",
            type: "POST",
            processData: false,
            contentType: false,
            data: formData,
            success: my_albumupload_success_albumupload
        });
    } else {
        console.log(bname);
        console.log(bartist);
        console.log(blaunch);
        console.log(bthumg);
    }
}

function my_albumupload_success_albumupload(jobj) {
    if (jobj.state == RespState.success) {
        console.log("albumupload 성공했다 albummgmt로 보내라");
    }
}
