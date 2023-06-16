package kr.co.project.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.project.board.notice.NoticeService;
import kr.co.project.board.notice.NoticeVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService mService;

	@GetMapping("/login.do")
	public String login(Model model, @CookieValue(value="cookieId", required=false) String cookieId) {
		System.out.println(cookieId);
		model.addAttribute("cookieId", cookieId);
		return "member/login";
	}
	@PostMapping("/login.do")
	public String login(Model model, HttpSession sess, HttpServletResponse res,
						@RequestParam String id,
						@RequestParam String pwd,
						@RequestParam(value="checkid", required=false) String checkid
			) {
		System.out.println(id+":"+pwd);
		MemberVO login = mService.login(id, pwd);
		// 로그인이 성공했다면 login에는 객체가 담기고 실패하면 null이 담길 것,,
		// 로그인 성공 -> null이 아니다 -> 세션에 저장
		
		if (login == null) { // 로그인실패
			model.addAttribute("msg", "아이디 비밀번호가 올바르지 않습니다.");
			return "include/alert";
		} else { // 로그인 성공
			sess.setAttribute("loginSess", login); // 로그인 세션 저장
			// 쿠키처리
			int expire = 0;
			if ("1".equals(checkid)) { // 사용자가 아이디저장 체크
				expire = 60*60*24*30; // 30일 보관
			} else {
				expire = 0; // 0 -> 즉시 expire
			}
			
			Cookie cookie = new Cookie("cookieId", id);
			cookie.setMaxAge(expire); // 유효기간 쿠키에 저장(set)
			res.addCookie(cookie); // 서버에 저장할 수 없으니 사용자에게 저장
			
			return "redirect:/index.do";
		}
	}
	@GetMapping("/logout.do")
	public String logout(Model model, HttpSession sess) {
		// 세션 삭제
		sess.invalidate();
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/test/index.do");
		return "include/alert";
	}
	
	@GetMapping("/join.do")
	public String join() {
		
		return "member/join";
	}
	
	@GetMapping("/dupCheck.do")
	@ResponseBody
	public String dupCheck(@RequestParam String id) {
		return mService.dupCheck(id) == 0 ? "usable" : "notusable";
	}
	
	@PostMapping("/regist.do")
	public String regist(Model model, MemberVO vo) {
		int r = mService.insert(vo);
		if (r == 0) {
			model.addAttribute("msg", "가입 실패");
		} else {
			model.addAttribute("msg", "정상적으로 가입되었습니다.");
			model.addAttribute("url", "/test/index.do");
		}
		return "include/alert";
	}
	
	@GetMapping("/findId.do")
	public String findId() {
		
		return "member/findId";
	}
	@PostMapping("/findId.do")
	@ResponseBody
	public String findId2(MemberVO vo) {
		
		return mService.findId(vo);
	}
	@GetMapping("/findPwd.do")
	public String findPwd() {
		
		return "member/findPwd";
	}
	@PostMapping("/findPwd.do")
	@ResponseBody
	public String findPwd2(MemberVO vo) {
		String result = "";
		if (mService.findPwd(vo) == null) {
			result = "fail";
		} else {
			result = "success";
		}
		return result;
	}
	
	@Autowired
	NoticeService nService;
	
	@GetMapping("/mypage.do")
	public String mypage(Model model, HttpSession sess, NoticeVO nvo) {
		nvo.setMemberno(((MemberVO)sess.getAttribute("loginSess")).getNo());
		model.addAttribute("result", nService.index(nvo));
		return "member/mypage";
	}
}