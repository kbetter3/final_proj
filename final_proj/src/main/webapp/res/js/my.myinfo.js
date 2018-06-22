function my_myinfo_load() {
    my_myinfo_loaddata();
    
    $("#my-myinfo-uploader").on("click", my_myinfo_uploader);
}

function my_myinfo_loaddata() {
    $.ajax({
        type: "post",
        url: "member/myinfo",
        success: my_myinfo_success_loaddata
    });
}

function my_myinfo_success_loaddata(jobj) {
    if (jobj.state == RespState.data) {
        var member = jobj.member;
        
        $("#my-myinfo-id").val(member.id);
        $("#my-myinfo-email").val(member.email);
        $("#my-myinfo-power").val(member.power);
        $("#my-myinfo-regdate").val(member.regdate);
        $("#my-myinfo-voucher").val(member.voucher);
        $("#my-myinfo-downcount").val(member.downcount);
    }
}

function my_myinfo_uploader() {
}