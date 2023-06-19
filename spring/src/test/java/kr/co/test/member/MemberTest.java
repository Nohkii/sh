package kr.co.test.member;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {config.MvcConfig.class})
public class MemberTest {

	// Mapper 테스트
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void allList() {
		List<MemberVO> list = mapper.allList(new MemberVO());
		for (MemberVO vo : list) {
			log.info(vo.toString());
		}
	}
	@Test
	public void countTest() {
		log.info("총개수:"+mapper.count(new MemberVO()));
	}
	
	@Autowired
	MemberService service;
	@Test
	public void service() {
		List<MemberVO> list = service.addList(new MemberVO());
		log.info(list.size()+"");
	}
	
	// 등록테스트
	@Test
	public void insertTest() {
		MemberVO vo = new MemberVO();
		vo.setId("test");
		vo.setName("테스트");
		vo.setEmail("test@gmail.com");
		vo.setPwd("test1234");
		vo.setGender(1);
		vo.setFilename_org("내사진.jpg");
		vo.setFilename_real("1234565.jpg");
		int r = service.insert(vo);
		assertEquals(r, 1);
	}
}
