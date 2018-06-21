package spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UploaderInterceptor extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object obj = request.getSession().getAttribute("upower");
		
		log.debug("{}", request.getSession().getAttribute("upower"));
		
		if (obj != null && (int)obj == 2) {
			return true;
		} else {
			return false;
		}
	}
}
