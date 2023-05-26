package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
// 서로 참조되지 않도록
@Entity
@Table(name = "tbl_profile")
@EqualsAndHashCode(of = "fno")

public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fno;
	private String fname;
	private boolean current;
	
	@ManyToOne // 프로필이 여러개니까
	private Member member;
	// 프로필테이블 <- 회원정보되도록 Member클래스타입을 인스턴스변수로 추가함
}
