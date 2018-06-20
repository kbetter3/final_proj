function my_artistupload_radio() {
    if ($(this)[0] == $("#my-upload-artist-type1")[0]) {
        $("#my-upload-artist-member").val($("#my-upload-artist-name").val());
        $("#my-upload-artist-member").prop("readonly", true);
    } else {
        $("#my-upload-artist-member").prop("readonly", false);
    }
}

function my_artistupload_picture_select(e) {
//    var formData = new FormData($("#upload-form")[0]);
    var filename = $(e.currentTarget).val();

    if(filename.match(/(.jpg|.jpeg|.png|.gif)$/)){

        var formData = new FormData();
        formData.append("pic", $(this)[0].files[0]);

        $.ajax({
            url: "pictest",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            type: "POST",
            success: my_artistupload_success_picture_select
        });
    }else{
        $("#my-upload-artist-img").removeAttr("src")
        $(e.currentTarget).val("")
        alert("이미지(jpg, jpeg, png, gif) 파일만 업로드 가능합니다.")
    }
}

function my_artistupload_success_picture_select(jobj) {
    var fname = jobj.data;

    console.log("my-upload-artist-img에 src 걸기");
    console.log(jobj.data);
    $("#my-upload-artist-img").attr("src", "pictest2?fname=" + jobj.data);
}

function my_artistupload_upload() {
    var name = $("#my-upload-artist-name");
    var member = $("#my-upload-artist-member");
    var debutdate = $("#my-upload-artist-debut");
    var activitytype = $(".my-upload-input-radio:checked");
    var thumb = $("#my-upload-artist-picture");

    var bname = name.val().length > 0 ? true : false;
    var bmember = member.val().length > 0 ? true : false;
    var bdebutdate = debutdate.val().length > 0 ? true : false;
    var bactivitytype = activitytype.length > 0 ? true : false;
    var bthumb = thumb.val() != "" ? true : false;



    if (bname && bmember && bdebutdate && bactivitytype && bthumb) {
        var formData = new FormData($("form")[0]);

        $.ajax({
            url: "mgmt/artistupload",
            type: "POST",
            processData: false,
            contentType: false,
            data: formData,
            success: my_artistupload_success_artistupload
        });
    } else {
        console.log(bname);
        console.log(bmember);
        console.log(bdebutdate);
        console.log(bagency);
        console.log(bthumg);
    }
}

function my_artistupload_success_artistupload(jobj) {
    if (jobj.state == RespState.success) {
        my_mgmt_artistmgmt();
    }
}
