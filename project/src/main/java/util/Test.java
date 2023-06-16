package util;

import kr.co.project.board.notice.NoticeVO;

public class Test {

	public static void main(String[] args) {
		NoticeVO vo = new NoticeVO();
		vo.setPage(11);
		int totalPage=30;
		
		int endPage = (int)Math.ceil(vo.getPage() / 10.0) * 10;
		int startPage = endPage - 9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = totalPage > endPage ? true : false;

		System.out.println(endPage);
	}

}
