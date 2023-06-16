package kr.co.project.board.notice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.project.member.MemberVO;

@Controller
public class NoticeController {

	@Autowired
	NoticeService nservice;
	
	@GetMapping("/board/notice/index.do")
	public String index( NoticeVO param, Model model) {
		// param 파라미터 안에는 index.do(게시물목록)페이지에 나오는 페이지번호, 검색어, 검색타입이 들어갈 수 있음   
		model.addAttribute("result", nservice.index(param));
		return "/board/notice/index";
	}
	
	@GetMapping("/board/notice/write.do")
	// 글작성 버튼 클릭하는 것과 url에 직접 입력하는 것과 동일하게 작동
	// jsp > board > notice > write.jsp파일로 이동
	public void write() {
		
	}
	
	@PostMapping("/board/notice/insert.do")
	public String insert(Model model, NoticeVO vo, HttpSession sess) {
		MemberVO loginSess = (MemberVO)sess.getAttribute("loginSess");
		// getAtrribute는 가져오는 것 -> 성공했을 때 등록한 세션을 가져옴
		// 세션은 Object형이기 때문에 MemberVO형으로 강제 형변환을 시켜야함
		
		vo.setMemberno(loginSess.getNo());
		if (nservice.insert(vo)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "등록 실패");
		}
		return "include/alert";
	}
	@GetMapping("/board/notice/view.do")
	public String view(Model model, NoticeVO vo) {
		model.addAttribute("data", nservice.view(vo));
		return "board/notice/view";
	}
	@GetMapping("/board/notice/edit.do")
	public String edit(Model model, NoticeVO vo) {
		model.addAttribute("data", nservice.edit(vo));
		return "board/notice/edit";
	}
	@PostMapping("/board/notice/update.do")
	public String update(Model model, NoticeVO vo) {
		if (nservice.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
		} else {
			model.addAttribute("msg", "수정 실패");
		}
		return "include/alert";
	}
	@GetMapping("/board/notice/delete.do")
	public String delete(Model model, NoticeVO vo) {
		if (nservice.delete(vo)) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
		} else {
			model.addAttribute("msg", "삭제 실패");
		}
		return "include/alert";
	}
}