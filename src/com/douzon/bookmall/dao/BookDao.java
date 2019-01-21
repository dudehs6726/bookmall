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
import com.douzon.bookmall.vo.CategoryVo;

public class BookDao {
	
	//책 추가
	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			
			conn = getConnection();
			
			String sql = "insert into book values( " + 
					"null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookVo.getName());
			pstmt.setInt(2, bookVo.getPrice());
			pstmt.setLong(3, bookVo.getCategoryNo());
			
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
	
	//책 조회
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 4.SQL문 실행
			String sql = "select a.no, a.name, a.price, b.name as category_name " + 
					"  		from book a, category b " + 
					" 	   where a.category_no = b.no " + 
					"      order by a.no asc";
			rs =  stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				String categoryName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				
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
