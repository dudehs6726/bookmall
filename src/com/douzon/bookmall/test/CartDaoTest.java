package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		
//		insertCart(2L, 1L, 2);
//		insertCart(4L, 1L, 4);
//		insertCart(7L, 1L, 1);
		
		getCartList();
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

}
