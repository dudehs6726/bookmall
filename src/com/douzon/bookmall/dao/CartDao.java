package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;

public class CartDao {
	
	//카트 추가
	public boolean insert(CartVo cartVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			
			conn = getConnection();
			
			String sql = "insert into cart values( " + 
					"  ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, cartVo.getBookNo());
			pstmt.setLong(2, cartVo.getMemberNo());
			pstmt.setInt(3, cartVo.getCount());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//카트 조회
	public List<CartVo> getList() {
		List<CartVo> list = new ArrayList<CartVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 4.SQL문 실행
			String sql = "select b.name, c.name, b.price, a.count, (b.price * a.count) " + 
					"    	from cart a, book b, member c" + 
					"      where a.book_no = b.no" + 
					"        and a.member_no = c.no" +
					"      order by b.name asc";
			rs =  stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				String bookName = rs.getString(1);
				String memberName = rs.getString(2);
				int price = rs.getInt(3);
				int count = rs.getInt(4);
				int total = rs.getInt(5);
				
				CartVo vo = new CartVo();
				vo.setBookName(bookName);
				vo.setMemberName(memberName);
				vo.setPrice(price);
				vo.setCount(count);
				vo.setTotal(total);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// 1.JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2.연결하기(Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		
		return conn;
	}
}
