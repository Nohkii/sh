package org.zerock.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMaker<T> {
	private Page<T> result; // jpa에서 실행된 결과값
	private Pageable prevPage; // 이전 페이지 객체
	private Pageable nextPage; // 다음 페이지 객체
	private int currentPageNum; // 현재 페이지 번호
	private int totalPageNum; // 총 페이지 수

	private Pageable currentPage; // 현재 페이지 객체
	private List<Pageable> pageList; // Pageable 객체들이 담길 리스트

	public PageMaker(Page<T> result) {
		this.result = result;// jpa에서 실행된 결과값
		this.currentPage = result.getPageable();
		this.currentPageNum = currentPage.getPageNumber() + 1;
		this.totalPageNum = result.getTotalPages();
		this.pageList = new ArrayList();
		calcPages();
	}

	private void calcPages() {
		// 임시 마지막 번호
		int tempEndNum = (int) (Math.ceil(this.currentPageNum / 10.0) * 10);
		// 시작 번호
		int startNum = tempEndNum - 9;
		Pageable startPage = this.currentPage;
		for (int i = startNum; i < this.currentPageNum; i++) {
			startPage = startPage.previousOrFirst();
		}
		// 이전 버튼 여부 체크
		this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();
		// 총 페이지 수보다 임시 마지막 번호가 크면~
		if (this.totalPageNum < tempEndNum) {
			tempEndNum = this.totalPageNum; // 마지막 번호를 총 페이지수로 대임
			this.nextPage = null; // 다음 버튼이 안나오게 하려고
		}
		// 시작 번호부터 마지막 번호까지 페이지 객체를 리스트에 저장(add해서 한다~)
		for (int i = startNum; i <= tempEndNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		// 다음 버튼 체크 여부
		this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage : null;
	}

}
