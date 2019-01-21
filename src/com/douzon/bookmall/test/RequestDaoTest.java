package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.RequestDao;
import com.douzon.bookmall.vo.RequestBookVo;
import com.douzon.bookmall.vo.RequestVo;

public class RequestDaoTest {

	public static void main(String[] args) {
		
		//insertRequest("부산광역시 해운대구 센텀동로 41 센텀벤처타운", 1L);
		
		long requestNo = 0;
		requestNo = getRequestList(1);
		getRequestBookList(requestNo);

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
