package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	@Query("select b from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
	public List<Board> findByTitle(String title);
	@Query(value="select * from tbl_boards where title like concat('%',?1,'%') and bno > 0 order by bno desc", nativeQuery=true)
	public List<Board> findByTitle3(String title);
		/* 메소드 이름에 따라 자동으로 쿼리를 생성하여 제목(title)을 조회함
	 * 쿼리 메소드 작성 규칙
	 * 1. 메소드 이름은 'findBy'로 시작해야 함
	 * 2. 그 뒤에 조회하려는 엔티티의 속성 이름을 CamelCase로 작성
	 * 3. 메소드 파라미터로 조회 조건을 전달. 
	 * 이 예시에서는 String title이 제목으로 조회하는 조건
	 */
	public List<Board> findByWriter(String writer);
		// 여러 개니까 컬렉션이나 위처럼 List로 해도 됨
	public List<Board> findByWriterContaining(String writer);
	public List<Board> findByWriterContainingOrContentContaining(String writer, String content);
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	// 페이징 처리
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
}
