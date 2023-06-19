package kr.co.test.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.member.MemberMapper;
import kr.co.test.member.MemberVO;

@RestController // 전체메서드 @ResponseBody
@RequestMapping("/api")
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:3000"}) // 허용
// 주소창링크 + 포트번호까지 아마 바꿔야될걸,,
public class ApiController {

	@GetMapping("/test")
	//@ResponseBody
	public String test() {
		return "test";
	}
	
	@GetMapping("/member")
	public MemberVO member() { // 객체를 리턴한다~~ 
		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setName("홍길동");
		return vo;
	}
	
	// Test에서 확인할 수 있도록,,

	@Autowired
	MemberMapper mapper; // 이걸로 디비에 접근한다~~ api할 때는 서비스를 굳이 만들필요가 없다,,
	
	@GetMapping("/member/all")
	public List<MemberVO> all(MemberVO vo) { // 커맨드 객체로 파라미터 받기..
		return mapper.allList(vo); // 위에서 주입받아서 가지고있는 메서드를 호출한다~~
	}
	
	// /api/member/10 -> 10번 회원이 출력
	@GetMapping("/member/{memberno}")
	public MemberVO selectOne(@PathVariable int memberno) {
		return mapper.selectOne(memberno);
	}
	
	// 등록
	// post로 전송을 할 때는 body에 값을 넣어서 전송을 할 수 있음,,
	// 매개변수에 key-value로 되어있는 map을 담으면 json과 형식이 같아서 알아서 담긴다~
	@PostMapping("/member/insert")
	public void post(@RequestBody Map<String, Object> param) {
		Iterator key = param.keySet().iterator();
		while (key.hasNext()) {
			String k = (String)key.next();
			System.out.println(k+":"+param.get(k)); // key - value 짝으로 이루어져있음,,
		}
	}
	@PutMapping("/member/insert2")
	public String post2(@RequestBody MemberVO vo) { // 얘도 커맨드객체에 담음
		return mapper.insert(vo) > 0 ? "success" : "fail";
	}
	@PostMapping("/member/insert3")
	public String insert3(MemberVO vo) {
		return mapper.insert(vo) > 0 ? "success" : "fail";
	}
	
	// 상태코드 응답
	@PutMapping("/member/insert3")
	public ResponseEntity<MemberVO> post3(@RequestBody MemberVO vo) {
		System.out.println(vo);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}
	
	@GetMapping("/member/list")
	public ResponseObject memberList(MemberVO vo) {
		ResponseObject ro = new ResponseObject();
		ro.setTotalcount(mapper.count(vo));
		ro.setItems(mapper.allList(vo));
		return ro;
	}
	
}
