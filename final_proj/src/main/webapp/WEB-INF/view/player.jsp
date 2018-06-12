<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<c:set var="rootPath" value="${pageContext.request.contextPath }"></c:set>
	
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
        </style>
        
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-latest.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
        <script src="${rootPath}/res/js/jplayer/jquery.jplayer.min.js"></script>
        
    </head>

    <body>
        <div style="position: relative; height: 40px;">
            <div style="width: 100%; min-width: 1060px; position: absolute; bottom: 0;">
                <div id="jquery_jplayer_1" class="jp-jplayer"></div>
                <div id="jp_container_1" class="jp-audio" role="application">
                    <button class="jp-play" role="button" tabindex="0">play</button>
                    <button class="jp-stop" role="button" tabindex="0">stop</button>
                    <button id="mybtn" role="button">another</button>
                </div>
            </div>
        </div>
        
        
        <script>
            $(document).ready(function(){
                $("#jquery_jplayer_1").jPlayer({
		          ready: function (event) {
			     $(this).jPlayer("setMedia", {
				title: "Bubble",
				mp3: "music?name=" + "test",
			});
		},
		swfPath: "${rootPath}/res/js/jplayer",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: true,
		toggleDuration: true
	});
                $("#mybtn").on("click", function(){
                	$("#jquery_jplayer_1").jPlayer("setMedia", {mp3: "music?name=" + $(this).text()}).jPlayer("play");
                });
            });
            
            
        </script>
    </body>
</html>