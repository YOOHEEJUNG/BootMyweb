package com.coding404.myweb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	

	@GetMapping("/productReg")
	public String reg() {
		return "product/productReg";
	}
	
	@GetMapping("/productList")
	public String list(HttpSession session,
						Model model,
						Criteria cri) {/* HttpServletRequest request */
		
		//프로세스 
		//admin이라고 가정(사용할 값이 현재 없기 때문에)
		session.setAttribute("user_id", "admin");
		
		//로그인한 회원만 조회
		System.out.println(cri.toString());
		
		String user_id = (String)session.getAttribute("user_id");
		ArrayList<ProductVO> list = productService.getList(user_id, cri);
		model.addAttribute("list", list);
		
		//페이지네이션 처리
		int total = productService.getTotal(user_id);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("pageVO", pageVO);
		
		
		return "product/productList";
	}
	
	@GetMapping("/productDetail")
	public String detail() {
		return "product/productDetail";
	}
	
	//등록 버튼 누르면 넘어가는 화면 
	@PostMapping("/registForm")
	public String registForm(/*Valid*/ ProductVO vo,
							RedirectAttributes ra) {
		
		int result = productService.regist(vo);
		System.out.println(result);
		String msg = result == 1 ? "정상적으로 입력되었습니다" : "등록에 실패하였습니다";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/product/productList"; //목록화면으로
		
	}
}
