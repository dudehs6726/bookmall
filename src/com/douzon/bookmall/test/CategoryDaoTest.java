package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {

//		insertCategory("소설");
//		insertCategory("인문");
//		insertCategory("예술");
//		insertCategory("사회");
//		insertCategory("자기계발");
//		insertCategory("여행");
//		insertCategory("IT 모바일");
		
		getCategoryList();

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

}
