package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_boards")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String writer;
	private String content;

	@CreationTimestamp
	// 생성할 때 날짜를 지정하지않아도 자동으로 들어가게하는 어노테이션(원래 테이블 옵션에 있음)
	private Timestamp regdate;
	
	@UpdateTimestamp
	// 수정할 때~ 
	private Timestamp updatedate;
}
