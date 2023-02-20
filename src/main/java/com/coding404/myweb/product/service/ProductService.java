package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	public int regist (ProductVO vo);
	public ArrayList<ProductVO> getList(String user_id, 
										Criteria cri); //조회(특정회원만 조회)
	
	
	
	public int getTotal(String user_id, Criteria cri);

	//카테고리 대분류 처리
	public List<CategoryVO> getCategory();
	//카테고리 중분류, 소분류 처리
	public List<CategoryVO> getCategoryChild(CategoryVO vo);



}
