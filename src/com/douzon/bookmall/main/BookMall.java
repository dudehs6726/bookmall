package com.douzon.bookmall.main;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.dao.RequestDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;
import com.douzon.bookmall.vo.CategoryVo;
import com.douzon.bookmall.vo.MemberVo;
import com.douzon.bookmall.vo.RequestBookVo;
import com.douzon.bookmall.vo.RequestVo;

public class BookMall {

	public static void main(String[] args) {
		
		//고객 추가
		insertMember("홍길동", "010-3423-9067", "hong@bindang.com", "hong1234");
		insertMember("임꺽정", "010-5478-9634", "im5555@gmail.com", "im5555");
		
		//고객 조회
		getMemberList();
		
		//카테고리 추가
		insertCategory("소설");
		insertCategory("인문");
		insertCategory("예술");
		insertCategory("사회");
		insertCategory("자기계발");
		insertCategory("여행");
		insertCategory("IT 모바일");
		
		//카테고리 조회
		getCategoryList();
		
		//도서 추가
		insertBook("피뢰침", 12420, 1);
		insertBook("막차의 신", 12600, 1);
		insertBook("나의 사랑, 매기", 10080, 1);
		
		insertBook("우리 고전 읽는 법", 9000, 2);
		insertBook("골목 도쿄", 15120, 2);
		insertBook("노인은 없다", 14220, 2);
		
		insertBook("고양이 그려볼테냥?", 13500, 3);
		insertBook("케이팝의 작은 역사", 11700, 3);
		insertBook("환송대", 31500, 3);
		
		insertBook("배틀그라운드", 13500, 4);
		insertBook("달빛 노동 찾기", 12600, 4);
		insertBook("시나리오 한반도", 14400, 4);
		
		insertBook("공부에 미친 사람들", 13500, 5);
		insertBook("사랑한다면 거리를 두는 게 좋아", 12400, 5);
		insertBook("그래서 오늘 마카롱을 먹기로 했다.", 13320, 5);
		
		insertBook("당신의 그 미소가 좋아서", 13050, 6);
		insertBook("후쿠오카 100배 즐기기", 14400, 6);
		insertBook("온갖 날의 미식 여행", 12600, 6);
		
		insertBook("R을 활용한 데이터 과학", 30600, 7);
		insertBook("인텔리제이 IDEA", 25200, 7);
		insertBook("빅데이터 분석과 활용", 23400, 7);

		//도서 조회
		getBookList();
		
		//카트 추가
		insertCart(2L, 1L, 2);
		insertCart(4L, 1L, 4);
		insertCart(7L, 1L, 1);
		
		//카트 조회
		getCartList();
		
		//주문 추가
		insertRequest("부산광역시 해운대구 센텀동로 41 센텀벤처타운", 1L);
		
		//주문 조회 및 주문도서 조회
		long requestNo = 0;
		requestNo = getRequestList(1);
		getRequestBookList(requestNo);

	}
	
	//고객 추가
	public static void insertMember(String name, String phoneNumber, String eMail, String passWord) {
		MemberVo vo = new MemberVo();
		
		vo.setName(name);
		vo.setPhoneNumber(phoneNumber);
		vo.seteMail(eMail);
		vo.setPassWord(passWord);
		
		new MemberDao().insert(vo);
	}
	
	//고객 조회
	public static void getMemberList() {
		List<MemberVo> list = new MemberDao().getList();
		
		System.out.println("=============>고 객 목 록 <=============");
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("==================================== ");
	}
	
	//카테고리 추가
	public static void insertCategory(String name) {
		CategoryVo vo = new CategoryVo();
		
		vo.setName(name);
		
		new CategoryDao().insert(vo);
	}
	
	//카테고리 조회
	public static void getCategoryList() {
		List<CategoryVo> list = new CategoryDao().getList();
		
		System.out.println("=============> 카 테 고 리 <=============");
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("=====================================");
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
	
	//카트 추가
	public static void insertCart(Long bookNo, Long memberNo, int count) {
		CartVo vo = new CartVo();
		
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setCount(count);
		
		new CartDao().insert(vo);
	}
	
	//카트 조회
	public static void getCartList() {
		List<CartVo> list = new CartDao().getList();
		
		System.out.println("=============> 카 트 목 록 <=============");
		for(CartVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("===================================== ");
	}
	
	//주문 추가
	public static void insertRequest(String address, Long memberNo) {
		RequestVo vo = new RequestVo();
		
		vo.setAddress(address);
		vo.setMemberNo(memberNo);
		
		new RequestDao().insert(vo);
	}
	
	//주문 조회
	public static long getRequestList(int memberNo) {
		List<RequestVo> list = new RequestDao().getList(memberNo);
		
		System.out.println("=============>주 문 목 록 <=============");
		for(RequestVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("==================================== ");
		
		if(list.size() > 0)
			return list.get(0).getNo();
		else
			return 0;
	}
	
	//주문도서 조회
	public static void getRequestBookList(long requestNo) {
		List<RequestBookVo> list = new RequestDao().getBookList(requestNo);
		
		System.out.println("=============>주 문 도 서 목 록 <=============");
		for(RequestBookVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("======================================== ");
	}

}
