package kr.co.test.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> addList(MemberVO vo) {
		//System.out.println(mapper.getClass().getName());
		log.debug(mapper.getClass().getName());
		int total = mapper.count(vo);
		log.debug("total:"+total);
		return mapper.allList(vo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int insert(MemberVO vo) {
		log.info("시퀀스 전:"+vo.getMemberno());
		int r = mapper.insert(vo);
		log.info("시퀀스 후:"+vo.getMemberno());
		if (r > 0) {
			HobbyVO hvo = new HobbyVO();
			hvo.setMemberno(vo.getMemberno());
			for (int hobby : vo.getHobby()) {
				hvo.setHobby(hobby);
				mapper.insertHobby(hvo);
			}
		}
		return r;
	}
}
