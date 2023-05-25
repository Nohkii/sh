package org.zerock;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@Log
@AutoConfigureMockMvc
@SpringBootTest
public class WebBoardRepositoryTests {
	
	@Autowired
	WebBoardRepository repo;

	//@Test
	void insertBoardDummies() {
		IntStream.range(0,300).forEach(i->{
			WebBoard board = new WebBoard();
			board.setTitle("sample board title"+i);
			board.setContent("content sample.."+i+"of board");
			board.setWriter("user0"+(i%10));
			repo.save(board);
		});
	}
	@Test
	public void testList1() {
		Pageable pageable = PageRequest.of(0,20,Direction.DESC,"bno");
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null),pageable);
		
		log.info("PAGE: " +result.getPageable());
		log.info("-------------------------------");
		
		// result.getContent().forEach(b->log.info(b+""));
		// 이렇게 할 수도 있고 아래처럼 할 수도 있다~ (List가 원래 쓰던 방식)
		List<WebBoard> list = result.getContent();
		for(WebBoard b : list) {
			log.info(b+"");
		}
	}
}
		
//		@Test
//		public void testList2() {
//			Pageable pageable = PageRequest.of(0,20,Direction.DESC,"bno");
//			Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"),pageable);
//			
//			log.info("PAGE: " +result.getPageable());
//			log.info("-------------------------------");
//			
//			result.getContent().forEach(b->log.info(b+""));
//			}
		
	
