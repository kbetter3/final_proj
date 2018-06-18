function my_artistupload_radio() {
    if ($(this)[0] == $("#my-upload-artist-type1")[0]) {
        $("#my-upload-artist-member").val($("#my-upload-artist-name").val());
        $("#my-upload-artist-member").prop("disabled", true);
    } else {
        $("#my-upload-artist-member").prop("disabled", false);
    }
}
