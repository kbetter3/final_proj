<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>title</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

        <style>
        /* style */
            .align-helper {
                display: inline-block;
                height: 100%;
                vertical-align: middle;
            }

            body {
                background-color: rgba(250, 250, 250, 1);
            }

            .my-wrap {
                width: 550px;
                margin: 0 auto;
                text-align: center;
            }

            .my-container {
                box-sizing: border-box;
                width: 100%;
                padding: 20px 50px;
                background-color: rgba(255, 255, 255, 1);
                border: 1px solid rgba(0, 0, 0, 0.13);
            }

            .my-container:nth-child(2n) {
                padding: 0 50px;
                margin-top: -1px;
            }

            .my-container > h2 {
                margin: 15px 0 30px 0;
            }

            .my-container > hr {
                margin: 0;
                padding: 0;
                border-top: 1px solid rgba(0, 0, 0, 0.13);
            }

            .my-row {
                display: block;
                position: relative;
                width: 100%;
                height: 62px;
                margin: 5px 0;
            }

            .my-row > * {
                margin: 0;
                font-size: 1.2em;
            }

            .my-reg-input > * {
                position: absolute;
            }

            .my-reg-input > label {
                bottom: 19px;
                left: 0;
            }

            .my-reg-input > input {
                box-sizing: border-box;
                border: 1px solid rgba(0, 0, 0, 0.26);
                border-radius: 10px;
                padding: 0 20px;
                width: 320px;
                height: 60px;
                right: 0;
                outline: none;
            }

            .my-reg-input > input:focus {
                outline: none;
            }

            .my-reg-input:nth-child(5n) {
                display: inline-block;
                height: 40px;
            }

            .my-reg-input:nth-child(5n) > * {
                position: static;
                display: inline-block;
                vertical-align: middle;
            }

            input[type=checkbox] {
                transform: scale(2);
                margin: 0;
                margin-right: 10px;
            }

            .my-term {
                margin: 14px 0;
                height: 40px;
                text-align: left;
            }

            .my-term > * {
                margin: 0;
                padding: 0;
                display: inline-block;
                vertical-align: middle;
            }

            .my-term > label > .term-option {
                font-size: 0.8em;;
                margin: 0 5px;
            }

            .required {
                color: rgba(0, 180, 35, 1);
            }

            .optional {
                color: rgba(125, 125, 125, 1);
            }

            .my-term > button {
                border: none;
                background: none;
                box-sizing: border-box;
                width: 40px;
                height: 40px;
                font-size: 1.8em;
            }

            .my-term > button:focus {
                outline: none;
            }

            .my-container > input[type=submit] {
                border: none;
                margin: 15px 0 40px 0;
                background-color: rebeccapurple;
                color: white;
                padding: 20px 90px;
                font-size: 1.5em;
            }

            input.green-border {
                border-color: rgba(0, 180, 35, 1);
            }

            input.red-border {
                border: 1px solid red;
            }
        </style>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

        <script>
            $(document).ready(function(){
                $("#id").on("keyup", idCheck);
            });

            function idCheck() {
                var regex = /^[a-zA-Z0-9]{4,20}$/
                var id = $(this).val();

                if (regex.test(id)) {
                    $(this).removeClass("red-border").addClass("green-border");
                } else {
                    $(this).removeClass("green-border").addClass("red-border");
                }
            }

            function emailCheck() {

            }
        </script>
    </head>

    <body>
        <div class="my-wrap">
            <h1>Logo</h1>
            <form action="register" method="post">
                <div class="my-container">
                    <h2>회원가입</h2>
                        <div class="my-row my-reg-input">
                            <label for="id">아이디</label>
                            <input type="text" name="id" id="id" placeholder="아이디 (4~20자)">
                        </div>

                        <div class="my-row my-reg-input">
                            <label for="pw">비밀번호</label>
                            <input type="password" name="pw" id="pw" placeholder="비밀번호 (8~20자)">
                        </div>

                        <div class="my-row my-reg-input">
                            <label for="pwchck">비밀번호 확인</label>
                            <input type="password" name="pwchck" id="pwchck" placeholder="비밀번호 확인">
                        </div>

                        <div class="my-row my-reg-input">
                            <div class="align-helper"></div>
                            <div>이메일 인증 후 가입이 완료됩니다.</div>
                        </div>

                        <div class="my-row my-reg-input">
                            <label for="email">이메일</label>
                            <input class="email" id="email" name="email" type="text" placeholder="email@example.com (100자이내)">
                        </div>

                </div>

                <div class="my-container">
                    <div class="my-row my-term">
                        <div class="align-helper"></div>
                        <input type="checkbox" id="checkall">
                        <label for="checkall">전체동의<span class="term-option optional">(선택사항 포함)</span></label>
                    </div>

                    <hr>

                    <div class="my-row my-term">
                        <div class="align-helper"></div>
                        <input type="checkbox" id="term1" name="term1">
                        <label for="term1">이용약관<span class="term-option required">(필수)</span></label>
                        <button type="button">+</button>
                    </div>
                    <div class="my-row my-term">
                        <div class="align-helper"></div>
                        <input type="checkbox" id="term2" name="term2">
                        <label for="term2">유료서비스약관<span class="term-option required">(필수)</span></label><button type="button">+</button>
                    </div>
                    <div class="my-row my-term">
                        <div class="align-helper"></div>
                        <input type="checkbox" id="term3" name="term3">
                        <label for="term3">개인정보 수집 및 이용<span class="term-option required">(필수)</span></label><button type="button">+</button>
                    </div>
                    <div class="my-row my-term">
                        <div class="align-helper"></div>
                        <input type="checkbox" id="term4" name="term4">
                        <label for="term4">이벤트, 서비스안내 수신<span class="term-option optional">(선택)</span></label><button type="button">+</button>
                    </div>

                    <input type="submit" value="가입하기">
                </div>
            </form>
        </div>
    </body>
</html>
