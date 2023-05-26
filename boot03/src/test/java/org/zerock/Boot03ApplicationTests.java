package org.zerock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.domain.Board;
import org.zerock.domain.QBoard;
import org.zerock.persistence.BoardRepository;

import com.querydsl.core.BooleanBuilder;

@AutoConfigureMockMvc
@SpringBootTest
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;
	// 인터페이스에서 만든 메서드가 여기 빈 컨테이너에 들어가있다~ 라고 생각하면 된다,,
	
//	@Test
//	public void testInsert200() {
//		for (int i=1; i<=200; i++) {
//			Board b = new Board();
//			b.setTitle("제목..."+i);
//			b.setContent("내용..."+i+" 채우기");
//			b.setWriter("user0"+(i%10));
//			repo.save(b);
//		}
//	}

	@Test
	public void testByTitle() {
		repo.findByTitle3("제목...177").forEach(b->System.out.println(b));
	}
	@Test
	public void testByWriter() {
		repo.findByWriter("user00").forEach(b->System.out.println(b));
	}
	@Test
	public void testByWriterContaining() {
		repo.findByWriterContaining("05").forEach(b->System.out.println(b));
	}
	@Test
	public void testByWriterContainingOrContentContaining() {
		
		String stype = "title"; // all, title, content
		String sword = "05";
		repo.findByWriterContainingOrContentContaining(sword, sword).forEach(b->System.out.println(b));
	}
	
	@Test
	public void testBnoOrderByPaging() {
		int page = 3;
		Pageable paging = PageRequest.of(0, 10);
		repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging).forEach(b->System.out.println(b));
	}
	@Test
	public void testBnoPagingSort() {
		// 여러컬럼으로 정렬하고 싶을때
		Sort s = Sort.by(Sort.Order.asc("bno"), Sort.Order.desc("title"));
//		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
		Pageable paging = PageRequest.of(0, 10, s);
		Page<Board> result = repo.findByBnoGreaterThan(0L, paging);
		System.out.println("PAGE SIZE:"+result.getSize());
		System.out.println("TOTAL PAGES:"+result.getTotalPages());
		System.out.println("TOTAL COUNT:"+result.getTotalElements());
		System.out.println("NEXT:"+result.nextPageable());
		result.getContent().forEach(b->System.out.println(b));
	}
	@Test
	public void testPredicate() {
		String type = "a";
		String keyword = "17";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		if (type.equals("t")) {
			builder.and(board.title.like("%"+keyword+"%"));
		}
		builder.and(board.bno.gt(0L));
		
		Pageable paging = PageRequest.of(0, 10);
		Page<Board> result = repo.findAll(builder, paging);
		
		System.out.println("PAGE SIZE:"+result.getSize());
		System.out.println("TOTAL PAGES:"+result.getTotalPages());
		System.out.println("TOTAL COUNT:"+result.getTotalElements());
		System.out.println("NEXT:"+result.nextPageable());
		result.getContent().forEach(b->System.out.println(b));
	}
	
}
