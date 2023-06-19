package kr.co.test.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	// url이 똑같으면 안됨! 그런데 방식이 다르면 같아도 ㄱㅊ(Get/Post)
	@GetMapping("/member/index.do")
	public String index(Model model,
			// @RequestParam(value="searchWord", required = false) String searchWord
			MemberVO vo) {
		model.addAttribute("list", service.addList(vo));
		return "member/index";
	}

	@GetMapping("/member/write.do")
	public void write() {

	}

	@PostMapping("/member/insert.do")
	public void insert(HttpServletResponse res, HttpServletRequest req, MemberVO param,
			@RequestParam MultipartFile filename) throws Exception {
		// 파일업로드
		if (!filename.isEmpty()) { // 사용자가 파일을 첨부했다
			// 파일명
			System.out.println("========================" + param + "====================");

			if (param.getFilename_org() == null) {
				System.out.println("======================================1");
			} else {
				System.out.println("======================================2" + param.getFilename_org());
			}

			String org = filename.getOriginalFilename(); // 원본
			String ext = org.substring(org.lastIndexOf("."));
			String real = System.currentTimeMillis() + ext;
//			System.out.println(org+" "+ ext+" "+real);

			// 경로
//			String path = req.getRealPath("/upload/");
//			System.out.println(path);

			// 파일저장
//			filename.transferTo(new File(path+real));
			param.setFilename_org(org);
			param.setFilename_real(real);
			System.out.println("========================" + param + "====================");

			String uploadFolder = "D:\\java\\upload";

			File saveFile = new File(uploadFolder + "\\" + real);
			try {
				filename.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int r = service.insert(param);

		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (r > 0) {
			out.print("alert('가입되었습니다.');");
			out.print("location.href='index.do';");
		} else {
			out.print("alert('등록실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}

	@GetMapping("/member/login.do")
	public String login(HttpSession sess) {
		sess.setAttribute("loginSession", "홍길동");
		return "redirect:index.do";
	}

}
