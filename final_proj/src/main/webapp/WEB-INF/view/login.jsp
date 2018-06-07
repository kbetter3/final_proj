<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
        <div class="my-login-wrap">
            <h1>Logo</h1>
            <div class="my-member-container">
                <h2>Login</h2>
                <div class="my-member-row">
                    <div class="my-member-row-left">
                        <div class="align-helper h60"></div>
                        <label class="my-member-label" for="id">아이디</label>
                    </div>
                    <div class="my-member-row-right">
                        <div class="align-helper h60"></div>
                        <input class="my-member-input" type="text" id="id" name="id" placeholder="아이디">
                    </div>
                </div>
                
                <div class="my-member-row">
                    <div class="my-member-row-left">
                        <div class="align-helper h60"></div>
                        <label class="my-member-label" for="id">비밀번호</label>
                    </div>
                    <div class="my-member-row-right">
                        <div class="align-helper h60"></div>
                        <input class="my-member-input" type="password" id="id" name="id" placeholder="비밀번호">
                    </div>
                </div>
                
                <div class="my-member-row">
                    <button class="my-login-loginbtn" type="button">
                        <div class="align-helper h60"></div>
                        <span class="my-login-loginbtn-txt">로그인</span>
                    </button>
                </div>
                
                <div class="my-login-hr"></div>
                
                <div class="my-login-option-wrap">
                    <div class="my-login-option-left">
                        <div class="align-helper h20"></div>
                        <input type="checkbox" id="saveid" name="saveid">
                        <label for="saveid">아이디 저장</label>
                    </div>
                    <div class="my-login-option-right">
                        <div class="align-helper h20"></div>
                        <a class="my-login-option-link" href="#">아이디 찾기</a>
                        <span class="my-login-sep">|</span>
                        <a class="my-login-option-link" href="#">비밀번호 찾기</a>
                        <span class="my-login-sep">|</span>
                        <a class="my-login-option-link" href="#">회원가입</a>
                    </div>
                </div>
            </div>
        </div>
        
<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>        