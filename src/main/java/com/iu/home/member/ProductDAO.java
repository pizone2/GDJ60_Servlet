package com.iu.home.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.util.DBconnection;

import oracle.jdbc.driver.DBConversion;

public class ProductDAO {
	//getMax
	public int getProductNum()throws Exception{
		Connection con = DBconnection.getConnection();
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		int num = rs.getInt(1);
		
		DBconnection.disConnect(rs, con, st);
		return num;
	} 
	
	//상품옵션 조회
	public List<ProductOptionDTO> getProductOptionList()throws Exception{
		ArrayList<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		
		Connection con = DBconnection.getConnection();
		
		String Sql = "SELECT * FROM PRODUCOPTION "
				+ "ORDER BY OPTIONPRICE ASC";
		
		PreparedStatement st = con.prepareStatement(Sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setNumber(rs.getLong("OPTIONNUMBER"));
			productOptionDTO.setNum(rs.getLong("PRODUCTNUM"));
			productOptionDTO.setName(rs.getString("OPTIONNAME"));
			productOptionDTO.setPrice(rs.getLong("OPTIONPRICE"));
			productOptionDTO.setInventory(rs.getLong("OPTIONINVENTORY"));
			ar.add(productOptionDTO);
		
		}
		
		
		
		DBconnection.disConnect(rs, con, st);
		return ar;
	}
	
	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		try {
			List<ProductOptionDTO> ar = productDAO.getProductOptionList();
			
			System.out.println(ar.size() != 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	//제품을 조회
	//set 중복을 허용하지 않는다, hashset
	//map 키와 value 로 이루어져있다 , hashmap
	//다형성, Arraylist 를 List로 다형성으로 인한 변환,부모형으로 해야 서로 편함
	public List<ProductDTO> getProductList() throws Exception{
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection con = DBconnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTJUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO  productDTO = new ProductDTO();
			productDTO.setNum(rs.getInt("PRODUCTNUM"));
			productDTO.setName(rs.getString("PRODUCTNAME"));
			productDTO.setJumsu(rs.getDouble("PRODUCTJUMSU"));
			ar.add(productDTO);
			
			return ar;
		
		}
		
		DBconnection.disConnect(rs, con, st);
		return ar;
	}
	

	
	public int setAddProduct(ProductDTO productDTO)throws Exception{
		
		Connection con = DBconnection.getConnection();
		
		String sql="INSERT INTO PRODUCT  VALUES (?,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1,productDTO.getNum());
		st.setString(2,productDTO.getName());
		st.setString(3,productDTO.getDetail());
		st.setDouble(4,productDTO.getJumsu());
		
		int result = st.executeUpdate();
		
		DBconnection.disConnect(con, st);
		
		return result;
		
	}
	
	public int setAddProductOption(ProductOptionDTO productOptionDTO)throws Exception{
		
		Connection con = DBconnection.getConnection();
		
		String sql="INSERT INTO PRODUCOPTION  VALUES (?,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setLong(1,productOptionDTO.getNumber());
		st.setLong(2,productOptionDTO.getNum());
		st.setString(3,productOptionDTO.getName());
		st.setLong(4,productOptionDTO.getPrice());
		st.setLong(5,productOptionDTO.getInventory());
		
		int result = st.executeUpdate();
		
		DBconnection.disConnect(con, st);
		
		return result;
		
	}
//	public static void main(String[] args) {
//		ProductDAO productDAO = new ProductDAO();
//		try {
//			List<ProductDTO> ar = productDAO.getProductList();
//			
//			System.out.println(ar.size() != 0);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) {
//		ProductDTO productDTO = new ProductDTO();
//		ProductDAO productDAO = new ProductDAO();
//		productDTO.setNum(1);
//		productDTO.setName("kim");
//		productDTO.setDetail("adsfasdf");
//		productDTO.setJumsu(2.2);
//		
//		
//		try {
//			int result = productDAO.setAddProduct(productDTO);
//			System.out.println(result != 0);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
