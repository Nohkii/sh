package org.zerock.persistence;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{
	
	public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	
	// 지연로딩, 트랜잭션 -> 개수+1번 실행되는 문제를 해결하기 위해 JPQL 사용
	@Query("select b.bno, b.title, count(r) from FreeBoard b LEFT OUTER JOIN b.replies r where b.bno > 0 group by b")
	public List<Object[]> getPage(Pageable page);
	
	// 서브쿼리형태로 변경
	// 예)댓글수, 첨부파일개수, 좋아요수, 싫어요수 -> 5개 테이블 조인
	// 이런경우 서브쿼리를 쓰면 훨씬 간편
	@Query("select b.bno, b.title, (select count(r) from FreeBoardReply r where r.board=b) from FreeBoard b")
	public List<Object[]> getPage2(Pageable page);
}
