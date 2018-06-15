<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <script src="${rootPath}/res/js/my.header.js"></script>
        <script src="${rootPath}/res/js/my.chart.js"></script>
        <script src="${rootPath}/res/js/my.submenu.js"></script>
        
    </head>
        
    <body>
<!--        헤더 시작 지점-->
        <div class="my-header-wrap">
            <div class="my-header-top-deco"></div>
            <div class="my-header-nav">
                <div class="my-container">
                    <div class="my-header-nav-wrap">
                        <div class="my-header-nav-item-wrap my-header-nav-item-left">
                            <div class="align-helper h45"></div>
                            <div class="my-header-nav-item my-header-cursor" id="my-header-homebtn">HOME</div>
                            <div class="my-header-nav-item">
                                <span class="glyphicon glyphicon-time
     my-header-glyphicon"></span>
                                <span id="my-header-voucher">-/-</span>
                                <span class="glyphicon glyphicon-download-alt"></span>
                                <span id="my-header-downcnt">-/-</span>
                            </div>
                            <div class="my-header-nav-item my-header-cursor" id="my-header-voucherbtn">이용권 구매</div>
                        </div>

                        <div class="align-helper h45"></div>
                        <div class="my-header-nav-item-center">
                            <input type="search" class="my-header-nav-search" placeholder="Search...">
                            <span class="glyphicon glyphicon-search my-header-cursor" id="my-header-searchbtn"></span>
                        </div>
                        
                        <div class="my-header-nav-item-wrap my-header-nav-item-right">
                            <div class="align-helper h45"></div>
<!--                            로그인 여부에 따라 내용물이 변하는 컨테이너-->
                            <div id="my-header-right-container">
                                <span class="my-header-nav-item my-header-cursor" id="my-header-loginbtn">로그인</span>
                                <span class="my-header-nav-item my-header-cursor" id="my-header-regbtn">회원가입</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function(){
                $("#my-header-homebtn").on("click", my_header_home);
                $("#my-header-voucherbtn").on("click", my_header_voucher);
                $("#my-header-loginbtn").on("click", my_header_login);
                $("#my-header-regbtn").on("click", my_header_register);
            });
        </script>
        
<!--        메뉴 시작지점-->
        <div class="my-container" id="my-menu-container">
            <div class="my-menu-wrap">
                <ul class="my-menu-left">
                    <li class="my-menu-menuitem"><a href="${rootPath}/chart">차트</a></li>
                    <li class="my-menu-menuitem"><a href="${rootPath}/new">최신</a></li>
                    <li class="my-menu-menuitem"><a href="${rootPath}/genre">장르</a></li>
                </ul>
                <ul class="my-menu-right">
                    <li class="my-menu-menuitem"><a href="${rootPath}/mymusic">마이뮤직</a></li>
                    <li class="my-menu-menuitem"><a href="${rootPath}/notice">공지사항</a></li>
                </ul>
            </div>
        </div>

<!--        서브메뉴 시작지점-->
        <div class="my-container" id="my-submenu-container">
        </div>

<!--        컨텐츠 시작 지점-->        
        <div class="my-container" id="my-contents-container">
        </div>
        
        <script>
            $(document).ready(function(){
                my_submenu_chartsubmenu();
                my_chart_chart();
            });
        </script>
    </body>
</html>
