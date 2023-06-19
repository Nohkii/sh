//package kr.co.test.member;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class JsonTest {
//	// @Test // 왜 않되는데..따흐흑
//	public void test() {
//		// 객체 -> json
//		MemberVO vo = new MemberVO();
//		vo.setId("hong");
//		vo.setName("홍길동");
//		vo.setEmail("hong@gamil.com");
//		
//		ObjectMapper om = new ObjectMapper();
//		String json = om.writeValueAsString(vo);
//		System.out.println(json);
//		
//		// json -> 객체
//		MemberVO vo2 = om.readValue(json, MemberVO.class);
//		System.out.println(vo2);
//		
//		// 객체(리스트) -> json
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		list.add(vo);
//		list.add(vo2);
//		
//		String json2 = om.writeValueAsString(list);
//		System.out.println(json2);
//		
//		// json(배열) -> 객체
//		List<MemberVO> list2 = om.readValue(json2, new TypeReference<ArrayList<MemberVO>>() {});
//		System.out.println(list2);
//	}
//
//}
