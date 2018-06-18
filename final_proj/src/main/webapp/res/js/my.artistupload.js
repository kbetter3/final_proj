function my_artistupload_radio() {
    if ($(this)[0] == $("#my-upload-artist-type1")[0]) {
        $("#my-upload-artist-member").val($("#my-upload-artist-name").val());
        $("#my-upload-artist-member").prop("disabled", true);
    } else {
        $("#my-upload-artist-member").prop("disabled", false);
    }
}

function my_artistupload_picture_select() {
    var formData = new FormData();
    formData.append("pic", $(this)[0].files[0]);
    
    $.ajax({
        url: "pictest",
        data: {
            mFile : formData
        },
        processData: false,
        contentType: false,
        type: "POST",
        success: my_artistupload_success_picture_select
    });
}

function my_artistupload_success_picture_select(jobj) {
    var fname = jobj.data;
    
    $("#my-artistupload-img").css("background-image", "pictest2?fname="+fname);
}