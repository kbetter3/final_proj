<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/template/menu.jsp"></jsp:include>

        <div class="my-container">
        	<div class="my-sep h20"></div>
            <div class="my-submenu-wrap">
                <ul class="my-submenu-container">
                    <li class="my-submenu-menuitem-4 my-submenu-selected"><a class="my-submenu-menulink my-submenu-selected" href="#">실시간</a></li>
                    <li class="my-submenu-menuitem-4"><a class="my-submenu-menulink" href="#">월간</a></li>
                    <li class="my-submenu-menuitem-4"><a class="my-submenu-menulink" href="#">주간</a></li>
                    <li class="my-submenu-menuitem-4"><a class="my-submenu-menulink" href="#">일간</a></li>
                </ul>
            </div>

            <table class="my-chart-contents-wrap">
                <thead class="my-chart-title">
                    <tr class="my-chart-title-wrap">
                        <th id="my-chart-title-cb"><input type="checkbox"></th>
                        <th id="my-chart-title-no">NO</th>
                        <th id="my-chart-title-thumb"></th>
                        <th id="my-chart-title-musicinfo">곡정보</th>
                        <th id="my-chart-title-album">앨범</th>
                        <th id="my-chart-title-like">좋아요</th>
                        <th id="my-chart-title-listen">듣기</th>
                        <th id="my-chart-title-add">담기</th>
                        <th id="my-chart-title-down">다운</th>
                    </tr>
                </thead>
                <tbody class="my-chart-contents">
                    <tr class="my-chart-music-row">
                        <td class="my-chart-cb"><input type="checkbox"></td>
                        <td class="my-chart-no">1</td>
                        <td class="my-chart-thumb"><img src="http://via.placeholder.com/60x60"></td>
                        <td class="my-chart-musicinfo">
                            <div class="my-chart-name">name</div>
                            <div class="my-chart-artist">artist</div>
                        </td>
                        <td class="my-chart-album">album</td>
                        <td class="my-chart-like">
                            <button class="glyphicon glyphicon-heart-empty my-chart-likebtn"></button>
                            <span class="my-chart-likecount">0</span>
                        </td>
                        <td class="my-chart-listen"><button class="glyphicon glyphicon-play my-chart-listenbtn"></button></td>
                        <td class="my-chart-add"><button class="glyphicon glyphicon-plus my-chart-addbtn"></button></td>
                        <td class="my-chart-down"><button class="glyphicon glyphicon-download-alt my-chart-downbtn"></button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        
        <script>
            $(document).ready(function(){

            });
        </script>
    