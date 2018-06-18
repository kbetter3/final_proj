package spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MgmtInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (request.getSession().getAttribute("uid") != null) {
			// 로그인한 유저일 경우
			switch ((int)request.getSession().getAttribute("upower")) {
			case 2:	// 업로더가 요청한 경우
			case 9: // 관리자가 요청한 경우
				return true;
			default:
				break;
			}
		}
		
		return false;
	}
}
