package kr.co.test.member;

import java.util.List;

public interface MemberService {

	List<MemberVO> addList(MemberVO vo);
	int insert(MemberVO vo);
}
