package com.iu.home.member;

public class ProductService {

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		
		
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setNum(1);
		productDTO.setName("product1");
		productDTO.setDetail("asdf");
		productDTO.setJumsu(0.0);
		
		ProductOptionDTO productOptionDTO = new ProductOptionDTO();
		productOptionDTO.setNum(1L);
		productOptionDTO.setName("optionName1");
		productOptionDTO.setPrice(100L);
		productOptionDTO.setInventory(10L);
		productOptionDTO.setNumber(1L);
		
		ProductOptionDTO productOptionDTO2 = new ProductOptionDTO();
		productOptionDTO2.setNum(2L);
		productOptionDTO2.setName("optionName1");
		productOptionDTO2.setPrice(200L);
		productOptionDTO2.setInventory(20L);
		productOptionDTO2.setNumber(2L);
		
		try {
			int num = productDAO.getProductNum();
			
			productDTO.setNum(num);
			
			int result = productDAO.setAddProduct(productDTO);
			
			
			productOptionDTO.setNum((long) num);
			
			if(result>0) {
				productDAO.setAddProductOption(productOptionDTO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
