<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
        <div class="my-login-wrap">
            <div class="my-member-logo">
                <h1><a href="#">Logo</a></h1>
            </div>
            <div class="my-member-container">
                <h2 class="my-member-title">Login</h2>
                <form action="login" method="post" id="login-form">
                <div class="my-member-row">
                    <div class="my-member-row-left">
                        <div class="align-helper h60"></div>
                        <label class="my-member-label" for="my-login-id">아이디</label>
                    </div>
                    <div class="my-member-row-right">
                        <div class="align-helper h60"></div>
                        <input class="my-member-input" type="text" id="my-login-id" name="id" placeholder="아이디">
                    </div>
                </div>

                <div class="my-login-id-info">아이디를 입력하세요.</div>
                    
                <div class="my-member-row">
                    <div class="my-member-row-left">
                        <div class="align-helper h60"></div>
                        <label class="my-member-label" for="my-login-pw">비밀번호</label>
                    </div>
                    <div class="my-member-row-right">
                        <div class="align-helper h60"></div>
                        <input class="my-member-input" type="password" id="my-login-pw" name="pw" placeholder="비밀번호">
                    </div>
                </div>
                    
                <div class="my-login-pw-info">비밀번호를 입력하세요.</div>

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
                        <input type="checkbox" id="my-login-saveid" name="sid">
                        <label for="my-login-saveid">아이디 저장</label>
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
                </form>
                <div class="my-login-errormsg">${error}</div>
            </div>
        </div>

        <script>
            $(document).ready(function() {
                $("#my-login-id").on("keyup", login_idCheck);
                $("#my-login-pw").on("keyup", login_pwCheck);
                $("button.my-login-loginbtn").on("click", login_login);
            });
        </script>
        
<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>        