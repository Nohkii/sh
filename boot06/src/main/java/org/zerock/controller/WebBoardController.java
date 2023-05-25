package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;
import org.zerock.vo.PageMaker;
import org.zerock.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

	@Autowired
	private WebBoardRepository repo; // 주입받기~

	// VO 생성해서 하는 방법 -> 이게 더 낫다~
	@GetMapping("/list")
	public void list(PageVO vo, Model model) {
		// var page = vo.makePageable(0, "bno"); -> /타입추론/해서 사용할 수도 있다
		Pageable page = vo.makePageable(0, "bno");
		// PageVO객체를 사용해서 
		Page<WebBoard> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);
		log.info("" + page);
		log.info("" + result);
		model.addAttribute("result", new PageMaker(result));
		// 데이터~ 등등이 result에 들어있음
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") WebBoard vo) {
		// 폼 데이터를 바인딩하기위해 WebBoard 객체를 매개변수로 받음
		log.info("register get");
	}
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
		log.info("register post");
		// RedirectAttributes 객체를 매개변수로 받아 사용자에게 보여줄 메세지를 추가
		log.info(""+vo);
		repo.save(vo);
		// repo를 사용해서 WebBoard객체를 데이터베이스에 저장함
		rttr.addFlashAttribute("msg", "success"); 
		//  일회용 값으로 주는..(flash니까~)
		return "redirect:/boards/list"; 
		// 등록 후 /boards/list로 리다이렉트함
		// success면 메시지를 띄우려고하는거다~ 잘 사용하지는 않지만 알아두면 좋다,,
	}
	
	@GetMapping("/view")
	public String view(HttpServletRequest req, PageVO vo) {
		WebBoard board = repo.findById(Long.parseLong(req.getParameter("bno"))).get();
		req.setAttribute("vo", board);
		return "boards/view";
	}
}
// PageableDefault 이용해서 하는 방법
//		@GetMapping("/list")
//		public void list(
//				@PageableDefault(
//						direction = Sort.Direction.DESC, sort = "bno", 
//						size = 10,page = 0) Pageable page) {
//			log.info("list() called.." + page);
//		}
