package kr.co.test.api;

import java.util.Date;
import java.util.List;

import kr.co.test.member.MemberVO;
import lombok.Data;

@Data
public class ResponseObject {
	private int totalcount;
	private Date regdate;
	private List<MemberVO> items;
	
	public ResponseObject() {
		this.regdate = new Date();
	}
}
