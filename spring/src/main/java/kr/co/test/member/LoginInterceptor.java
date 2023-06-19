package kr.co.test.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession sess = request.getSession();
		if (sess.getAttribute("loginSession") == null) { // 미로그인
			// 세션이 없으면 안된다~ 알림
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 후 사용 가능합니다');");
			out.print("location.href= 'index.do';");
			out.print("</script>");
			return false;
		} else { // 로그인
			return true; // 가던 길 가라~~
		}
	} // 부모한테 있던거 가져와서 로그인하기 전에 ~되어있으면 -> true / 안되어있으면 -> false
}
