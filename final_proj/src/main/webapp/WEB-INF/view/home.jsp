<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            * {
                margin: 0;
                padding: 0;

                vertical-align: middle;

                box-sizing: border-box;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
            }

            .my-header-deco {
/*                width: 100%;*/
                height: 5px;
                background-color: rebeccapurple;
            }

            .my-header {
                height: 33px;
                box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
            }

            .my-nav-wrap {
                width: 100%;
                height: 160px;
            }

            .my-nav-container, .my-header-container{
                width: 1060px;
                height: 160px;
                padding: 0 15px 0 15px;
                margin: auto;
            }

            .my-nav-container {
                box-shadow: 0 1px rgba(0, 0, 0, 0.38);
            }

            .my-helper {
                display: inline-block;
                height: 100%;
                vertical-align: middle;
            }

            .my-nav-logo {
                display: inline-block;
                width: 140px;
                height: 100px;
                vertical-align: middle;
            }

            .my-search-bar {
                border: 3px solid rebeccapurple;
                border-radius: 60px;
                padding: 15px;
                display: inline-block;
                vertical-align: middle;
            }

            .my-nav-search {
                font-size: 1.5em;
                border: none;
                width: 320px;
                padding: 0;
                margin: 0;
                box-sizing: content-box;
            }

            .my-nav-search-button {
                padding: 0;
                margin: -5px 0 0 0;
                width: 30px;
                height: 30px;
                background-color: rgba(0, 0, 0, 0);
                border: none;
            }

            .my-main-content-wrap {
                padding-top: 30px;
            }

            .my-main-content-container {
                padding: 0 20px;
                width: 1060px;
                margin: auto;
            }

            .my-login-wrap {
                position: relative;
                width: 279px;
            }

            .my-login {
                font-size: 0.9em;
                padding: 0 10px;
                width: 200px;
                height: 40px;
            }

            .my-login-pw {
                margin: -1px 0 0 0;
            }

            .my-login-btn {
                font-size: 0.9em;
                position: absolute;
                right: 0;
                top: 0;
                width: 80px;
                height: 79px;
            }

            .my-login-bottom {
                position: relative;
                height: 40px;
            }

            .my-login-bottom > * {
                font-size: 0.8em;
                margin: 0;
                display: inline-block;
                vertical-align: middle;
            }

            .my-login-bottom > a {
                display: inline-block;
                vertical-align: middle;
            }

            input[type=checkbox] {
                margin-top: 0;
            }

            .my-member-find {
                position: absolute;
                right: 0;
                vertical-align: middle;
            }

            .my-member-find-container > span {
                display: inline-block;
                white-space: nowrap;
                text-align: right;
                margin: 0 5px;
                vertical-align: middle;;
            }

            .my-member-find-container > * {
                display: inline-block;
            }
            
            .my-member-find-container {
                height: 40px;
                display: table-cell;
                vertical-align: middle;
            }
        </style>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

        <script>
            $(document).ready(function(){

            });
        </script>
    </head>

    <body>
        <div class="my-header-deco"></div>
        <div class="my-header">
            <div class="my-header-container">

            </div>
        </div>

        <div class="my-nav-wrap">
            <div class="my-nav-container">
                <span class="my-helper"></span>
                <img class="my-nav-logo" src="http://via.placeholder.com/100x160"/>

                <div class="my-search-bar">
                    <span class="my-helper"></span>
                    <input type="text" class="my-nav-search">
                    <button type="button" class="my-nav-search-button">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </div>
            </div>
        </div>

        <div class="my-main-content-wrap">
            <div class="my-main-content-container">
                <div class="my-login-wrap">
                    <form>
                        <input type="text" class="my-login my-login-id">
                        <input type="password" class="my-login my-login-pw">
                        <button type="button" class="my-login-btn">로그인</button>
                    </form>

                    <div class="my-login-bottom">
                        <span class="my-helper"></span>
                        <input type="checkbox" id="id-remember">
                        <label for="id-remember">아이디 저장</label>

                        <span class="my-member-find">
                            <span class="my-member-find-container">
                                <a href="register">회원가입</a>
                                <span class="my-seperator">|</span>
                                <a href="#">아이디/비밀번호 찾기</a>
                            </span>
                        </span>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
