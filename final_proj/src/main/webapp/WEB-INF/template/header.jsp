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

        <link rel="stylesheet" href="${rootPath}/res/css/my.common.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.header.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.member.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.login.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.register.css">
        
        <link rel="stylesheet" href="${rootPath}/res/css/my.menu.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.submenu.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.chart.css">

        <style>
        /* style */
        </style>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
        
        <script src="${rootPath}/res/js/jplayer/jquery.jplayer.min.js"></script>
        
        <script src="${rootPath}/res/js/my.encrypt.js"></script>
        <script src="${rootPath}/res/js/my.login.js"></script>
        <script src="${rootPath}/res/js/my.register.js"></script>
        
        <script>
            $(document).ready(function(){
            });
        </script>
    </head>

    <body>
        <div class="my-header-wrap">
            <div class="my-header-top-deco"></div>
            <div class="my-header-nav">
                <div class="my-container">
                    <div class="my-header-nav-wrap">
                        <div class="my-header-nav-item-wrap my-header-nav-item-left">
                            <div class="align-helper h45"></div>
                            <a class="my-header-nav-item" href="${rootPath}/home">HOME</a>
                            <div class="my-header-nav-item glyphicon glyphicon-time
 my-header-glyphicon">2018-01-28</div>
                            <div class="my-header-nav-item 	glyphicon glyphicon-download-alt">30</div>
                            <a class="my-header-nav-item" href="#">이용권 구매</a>
                        </div>

                        <div class="align-helper h45"></div>
                        <div class="my-header-nav-item-center">
                            <input type="search" class="my-header-nav-search" placeholder="Search...">
                            <a class="my-header-nav-item" href="#">
                                <span class="glyphicon glyphicon-search"></span>
                            </a>
                        </div>

                        <div class="my-header-nav-item-wrap my-header-nav-item-right">
                            <div class="align-helper h45"></div>
                            <a class="my-header-nav-item" href="${rootPath}/login">로그인</a>
                            <a class="my-header-nav-item" href="${rootPath}/register">회원가입</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
        