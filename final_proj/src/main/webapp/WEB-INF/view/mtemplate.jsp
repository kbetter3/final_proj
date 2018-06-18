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
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
		
        <link rel="stylesheet" href="${rootPath}/res/css/my.common.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.header.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.member.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.login.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.register.css">
        
        <link rel="stylesheet" href="${rootPath}/res/css/my.menu.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.submenu.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.chart.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.upload.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.voucher.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.player.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.artistmgmt.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.albummgmt.css">
        <link rel="stylesheet" href="${rootPath}/res/css/my.musicmgmt.css">
        
        
        

        <style>
        /* style */
        </style>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        
        <script src="${rootPath}/res/js/jplayer/jquery.jplayer.min.js"></script>
        
        <script src="${rootPath}/res/js/my.encrypt.js"></script>
        <script src="${rootPath}/res/js/my.login.js"></script>
        <script src="${rootPath}/res/js/my.register.js"></script>
        <script src="${rootPath}/res/js/my.header.js"></script>
        <script src="${rootPath}/res/js/my.chart.js"></script>
        <script src="${rootPath}/res/js/my.menu.js"></script>
        <script src="${rootPath}/res/js/my.submenu.js"></script>
        <script src="${rootPath}/res/js/my.state.js"></script>
        <script src="${rootPath}/res/js/my.player.js"></script>
        <script src="${rootPath}/res/js/my.mgmt.js"></script>
        
        <script src="${rootPath}/res/js/my.artistupload.js"></script>
        <script src="${rootPath}/res/js/my.albumupload.js"></script>
        <script src="${rootPath}/res/js/my.musicupload.js"></script>
        
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
            });
        </script>

<!--        메뉴 시작지점-->
        <div class="my-container" id="my-menu-container">
        </div>

<!--        서브메뉴 시작지점-->
        <div class="my-container" id="my-submenu-container">
        </div>

<!--        컨텐츠 시작 지점-->
        <div class="my-container" id="my-contents-container">
        </div>

		<div id="my-player-container"></div>
		
        <script>
            $(document).ready(function(){
                my_header_header();
                my_menu_menu();
                my_submenu_submenu("chartsubmenu");
                my_player_player();
            });
        </script>
    </body>
</html>
