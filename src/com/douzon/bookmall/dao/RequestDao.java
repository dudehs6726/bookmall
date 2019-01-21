package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.MemberVo;
import com.douzon.bookmall.vo.RequestBookVo;
import com.douzon.bookmall.vo.RequestVo;

public class RequestDao {
	//주문 추가
	public boolean insert(RequestVo requestVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			
			conn = getConnection();
			
			String sql = "insert into request( no, amount, address, member_no ) " + 
					"  select null, sum(b.price), ?, max(a.member_no) " + 
					"    from cart a, book b " + 
					"   where a.book_no = b.no " + 
					"     and a.member_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, requestVo.getAddress());
			pstmt.setLong(2, requestVo.getMemberNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			if(result) {
				sql = "insert into request_book( book_no, count, request_no ) " + 
						"  select b.no, a.count, (select max(c.no) " + 
						"  							from request c " + 
						"                           where c.member_no = ?) " + 
						"    from cart a, book b " + 
						"   where a.book_no = b.no " + 
						"     and a.member_no = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, requestVo.getMemberNo());
				pstmt.setLong(2, requestVo.getMemberNo());
				
				count = pstmt.executeUpdate();
				result = count == 1;
			}
			
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
	
	//주문 조회
	public List<RequestVo> getList(int memberNo) {
		List<RequestVo> list = new ArrayList<RequestVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 4.SQL문 실행
			String sql = "select a.no, a.amount, a.address, b.name, b.e_mail " + 
					"     from request a, member b " + 
					"    where a.member_no = b.no " + 
					"      and a.member_no = " + memberNo + " " +
					"    order by a.no asc";
			rs =  stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				long no = rs.getLong(1);
				int amount = rs.getInt(2);
				String address = rs.getString(3);
				String memberName = rs.getString(4);
				String eMail = rs.getString(5);
				
				RequestVo vo = new RequestVo();
				vo.setNo(no);
				vo.setAmount(amount);
				vo.setAddress(address);
				vo.setMemberName(memberName);
				vo.seteMail(eMail);
				
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
	
	//주문 도서 조회
	public List<RequestBookVo> getBookList(long RequestNo) {
		List<RequestBookVo> list = new ArrayList<RequestBookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 4.SQL문 실행
			String sql = "select a.book_no, b.name, a.count " + 
					"     from request_book a, book b " + 
					"    where a.book_no = b.no " + 
					"      and a.request_no = " + RequestNo
					+ "  order by a.book_no";
			rs =  stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				long bookNo = rs.getLong(1);
				String bookName = rs.getString(2);
				int count = rs.getInt(3);
				
				RequestBookVo vo = new RequestBookVo();
				vo.setBookNo(bookNo);
				vo.setBookName(bookName);
				vo.setCount(count);
				
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
