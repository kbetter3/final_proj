<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<div class="my-register-wrap">
            <div class="my-member-logo">
                <h1><a href="#">Logo</a></h1>
            </div>
            <div class="my-member-container">
                <h2 class="my-member-title">회원가입</h2>
                <form action="register" method="post" id="register-form">
                    <div class="my-member-row">
                        <div class="my-member-row-left">
                            <div class="align-helper h60"></div>
                            <label class="my-member-label" for="my-register-id">아이디</label>
                        </div>
                        <div class="my-member-row-right">
                            <div class="align-helper h60"></div>
                            <input class="my-member-input" type="text" id="my-register-id" name="id" placeholder="아이디 (4~20자)">
                            <button type="button" class="my-register-iddupchck">중복검사</button>
                        </div>

                    </div>
                    <div class="my-register-info-row" id="my-register-id-info"></div>

                    <div class="my-member-row">
                        <div class="my-member-row-left">
                            <div class="align-helper h60"></div>
                            <label class="my-member-label" for="my-register-pw">비밀번호</label>
                        </div>
                        <div class="my-member-row-right">
                            <div class="align-helper h60"></div>
                            <input class="my-member-input" type="password" id="my-register-pw" name="pw" placeholder="비밀번호 (8~20자)">
                        </div>
                    </div>

                    <div class="my-member-row">
                        <div class="my-member-row-left">
                            <div class="align-helper h60"></div>
                            <label class="my-member-label" for="my-register-pwchck">비밀번호 확인</label>
                        </div>
                        <div class="my-member-row-right">
                            <div class="align-helper h60"></div>
                            <input class="my-member-input" type="password" id="my-register-pwchck" name="pwchck" placeholder="비밀번호 확인">
                        </div>
                    </div>
                    <div class="my-register-info-row" id="my-register-pw-info"></div>

                    <div class="my-member-row">
                        <span class="align-helper h60"></span>
                        <span class="my-register-info" id="my-register-email-label">이메일 인증 후 가입이 완료됩니다.</span>
                    </div>

                    <div class="my-member-row">
                        <div class="my-member-row-left">
                            <div class="align-helper h60"></div>
                            <label class="my-member-label" for="my-register-pwchck">이메일</label>
                        </div>
                        <div class="my-member-row-right">
                            <div class="align-helper h60"></div>
                            <input class="my-member-input" type="text" id="my-register-email" name="email" placeholder="이메일">
                            <button type="button" class="my-register-emaildupchck">중복검사</button>
                        </div>
                    </div>
                    <div class="my-register-info-row" id="my-register-email-info"></div>
                </form>
            </div>
            <div class="my-member-container">
                <div class="my-register-term-row">
                    <div class="align-helper h40"></div>
                    <input type="checkbox" class="my-register-term-checkbox-all" id="my-register-term-all">
                    <label for="my-register-term-all">전체동의</label>
                    <span class="my-register-term-option my-register-gray-option">(선택사항 포함)</span>
                </div>
                <div class="my-register-term-hr"></div>
                <div class="my-register-term-row">
                    <div class="align-helepr h40"></div>
                    <input type="checkbox" class="my-register-term-checkbox" id="my-register-term-1">
                    <label for="my-register-term-1">이용약관</label>
                    <span class="my-register-term-option my-register-green-option">(필수)</span>
                    <button type="button" class="glyphicon glyphicon-chevron-down"></button>
                </div>
                <div class="my-register-term-content" id="my-register-content-1">abc</div>
                <div class="my-register-term-row">
                    <div class="align-helper h40"></div>
                    <input type="checkbox" class="my-register-term-checkbox" id="my-register-term-2">
                    <label for="my-register-term-2">유료서비스약관</label>
                    <span class="my-register-term-option my-register-green-option">(필수)</span>
                    <button type="button" class="glyphicon glyphicon-chevron-down"></button>
                </div>
                <div class="my-register-term-content" id="my-register-content-2">abc</div>
                <div class="my-register-term-row">
                    <div class="align-helper h40"></div>
                    <input type="checkbox" class="my-register-term-checkbox" id="my-register-term-3">
                    <label for="my-register-term-3">개인정보 수집 및 이용</label>
                    <span class="my-register-term-option my-register-green-option">(필수)</span>
                    <button type="button" class="glyphicon glyphicon-chevron-down"></button>
                </div>
                <div class="my-register-term-content" id="my-register-content-3">abc</div>
                <div class="my-register-term-row">
                    <div class="align-helper h40"></div>
                    <input type="checkbox" class="my-register-term-checkbox" id="my-register-term-4">
                    <label for="my-register-term-4">이벤트, 서비스안내 수신</label>
                    <span class="my-register-term-option my-register-gray-option">(선택)</span>
                    <button type="button" class="glyphicon glyphicon-chevron-down"></button>
                </div>
                <div class="my-register-term-content" id="my-register-content-4">abc</div>
                
                <div class="my-register-info-row" id="my-register-term-info"></div>

                <button type="button" class="my-register-regbtn">
                    <div class="align-helper h60"></div>
                    <span class="my-register-regbtntxt">등록하기</span>
                </button>
            </div>
        </div>

        <script>
            $(document).ready(function(){
                $(".my-register-iddupchck").on("click", register_idCheck);
                $("#my-register-id").on("keydown", register_resetIdValidation);
                $("input[type=password]").on("keyup", register_pwCheck);
                $(".my-register-emaildupchck").on("click", register_emailCheck);
                $("#my-register-email").on("keydown", register_resetEmailValidation);
                $(".my-register-term-checkbox").on("click", register_termCheck);
                $("#my-register-term-all").on("click", register_termAllCheck);
                $(".my-register-regbtn").on("click", register_doRegister);
                $(".my-register-term-clip").on("click", register_clipTerm);
            });
        </script>