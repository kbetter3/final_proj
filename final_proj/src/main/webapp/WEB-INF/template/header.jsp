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
        
    </head>

    <body>
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

                        <div class="my-header-nav-item-wrap my-header-nav-item-right" id="my-header-right-container">
                            <div class="align-helper h45"></div>
                            <div class="my-header-nav-item my-header-cursor" id="my-header-loginbtn">로그인</div>
                            <div class="my-header-nav-item my-header-cursor" id="my-header-regbtn">회원가입</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script>
            $(document).ready(function(){
                $("#my-header-homebtn").on("click", my_header_home);
            });
        </script>
<!--         
    </body>
</html>
-->