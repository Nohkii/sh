package kr.co.test.member;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
// 널아닌것만 처리
@JsonInclude(Include.NON_NULL)
public class MemberVO {
	@JsonProperty("memberNo")
	private int memberno;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Timestamp regdate;
	private int gender;
	private String filename_org;
	private String filename_real;
	
	private int[] hobby;
	private String searchWord;
	private int sGender;
	private String orderColumn; // 정렬할 컬럼
	private String order;		// asc / desc
	private String state;
	
	public MemberVO() {
		sGender = -1;
		orderColumn = "joindate";
		order = "DESC";
		filename_org = "";
		filename_real = "";
	}
}
