package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		
//		insertMember("홍길동", "010-3423-9067", "hong@bindang.com", "hong1234");
//		insertMember("임꺽정", "010-5478-9634", "im5555@gmail.com", "im5555");
		
		getMemberList();

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

}
