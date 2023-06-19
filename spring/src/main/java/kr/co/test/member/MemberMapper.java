package kr.co.test.member;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	List<MemberVO> allList(MemberVO vo);
	int count(MemberVO vo);
	int insert(MemberVO vo);
	int insertHobby(HobbyVO vo);
	MemberVO selectOne(int memberno);
}
