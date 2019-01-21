package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CategoryVo;

public class BookDaoTest {

	public static void main(String[] args) {
		

//		insertBook("피뢰침", 12420, 1);
//		insertBook("막차의 신", 12600, 1);
//		insertBook("나의 사랑, 매기", 10080, 1);
		
//		insertBook("우리 고전 읽는 법", 9000, 2);
//		insertBook("골목 도쿄", 15120, 2);
//		insertBook("노인은 없다", 14220, 2);
		
//		insertBook("고양이 그려볼테냥?", 13500, 3);
//		insertBook("케이팝의 작은 역사", 11700, 3);
//		insertBook("환송대", 31500, 3);
		
//		insertBook("배틀그라운드", 13500, 4);
//		insertBook("달빛 노동 찾기", 12600, 4);
//		insertBook("시나리오 한반도", 14400, 4);
		
//		insertBook("공부에 미친 사람들", 13500, 5);
//		insertBook("사랑한다면 거리를 두는 게 좋아", 12400, 5);
//		insertBook("그래서 오늘 마카롱을 먹기로 했다.", 13320, 5);
		
//		insertBook("당신의 그 미소가 좋아서", 13050, 6);
//		insertBook("후쿠오카 100배 즐기기", 14400, 6);
//		insertBook("온갖 날의 미식 여행", 12600, 6);
		
//		insertBook("R을 활용한 데이터 과학", 30600, 7);
//		insertBook("인텔리제이 IDEA", 25200, 7);
//		insertBook("빅데이터 분석과 활용", 23400, 7);

		getBookList();

	}
	
	//책 추가
	public static void insertBook(String name, int price, long categoryNo) {
		BookVo vo = new BookVo();
		
		vo.setName(name);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		
		new BookDao().insert(vo);
	}
	
	//책 조회
	public static void getBookList() {
		List<BookVo> list = new BookDao().getList();
		
		System.out.println("=============> 책 목 록 <=============");
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("=================================== ");
	}

}
