/**
 *  my.login.js
 */
var login_idNullCheck = false;
var login_pwNullCheck = false;

function login_idCheck() {
    var input = $("#my-login-id");
    var id = input.val();
    
    if (id.length === 0) {
        input.addClass("my-member-red-border");
        $(".my-login-id-info").show();
        login_idNullCheck = false;
    } else if (id.length > 20) {
        input.val(id.substr(0, 20));
    } else {
        $(".my-login-id-info").hide();
        input.removeClass("my-member-red-border");
        login_idNullCheck = true;
    }
}

function login_pwCheck() {
    var input = $("#my-login-pw");
    var pw = input.val();
    
    if (pw.length === 0) {
        input.addClass("my-member-red-border");
        $(".my-login-pw-info").show();
        login_pwNullCheck = false;
    } else {
        $(".my-login-pw-info").hide();
        input.removeClass("my-member-red-border");
        login_pwNullCheck = true;
    }
}

function login_login() {
    login_idCheck();
    login_pwCheck();
    
    if (login_idNullCheck && login_pwNullCheck) {
        login_encpw();
        $("#login-form").submit();
    }
}

function login_encpw() {
    var pw = $("#my-login-pw").val();
    var encpw = SHA256(pw);
    $("#my-login-pw").val(encpw);
}