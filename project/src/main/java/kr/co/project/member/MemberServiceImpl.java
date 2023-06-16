package kr.co.project.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;
	
	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public MemberVO login(String id, String pwd) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("pwd", pwd);
		return mapper.login(map);
	}

	@Override
	public int dupCheck(String id) {
		Map map = new HashMap();
		map.put("id", id);
		return mapper.dupCheck(map);
	}

	@Override
	public String findId(MemberVO vo) {
		return mapper.findId(vo);
	}

	@Override
	public MemberVO findPwd(MemberVO vo) {
		MemberVO result = mapper.findPwd(vo);
		if (result != null) {
			// 임시비밀번호 (영문2자+숫자2)
			String temp = "";
			for (int i=0; i<2; i++) {
				temp += (char)(Math.random()*26+65);
			}
			for (int i=0; i<2; i++) {
				temp += (int)(Math.random()*10);
			}
			System.out.println("임시비밀번호:"+temp);
			result.setPwd(temp);
			mapper.updateTempPwd(result);
			// 메일발송
			SendMail.send("withsky999@naver.com", result.getEmail(), "임시비밀번호", "임시비밀번호:<b>"+temp+"</b>");
			return result;
		}
		return null;
	}

}
