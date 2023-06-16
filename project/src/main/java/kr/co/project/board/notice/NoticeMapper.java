package kr.co.project.board.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	// 데이터베이스랑 연결시켜주는 애다~
	int insert(NoticeVO vo);
	int update(NoticeVO vo);
	int delete(NoticeVO vo);
	int count(NoticeVO vo);
	
	List<NoticeVO> list(NoticeVO vo);
	NoticeVO view(NoticeVO vo);
	int updateViewcount(int no);
}
