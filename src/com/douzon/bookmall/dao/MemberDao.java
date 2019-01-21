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

public class MemberDao {
	
	//고객 추가
	public boolean insert(MemberVo memberVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			
			conn = getConnection();
			
			String sql = "insert into member values( " + 
					" null, ?, ?, ?, password(?))";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPhoneNumber());
			pstmt.setString(3, memberVo.geteMail());
			pstmt.setString(4, memberVo.getPassWord());
			
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
	
	//고객 조회
	public List<MemberVo> getList() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 4.SQL문 실행
			String sql = "select a.no, a.name, a.phone_number, a.e_mail, a.password " + 
					"   	from member a " + 
					"  	   order by a.no asc";
			rs =  stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String phoneNumber = rs.getString(3);
				String eMail = rs.getString(4);
				String passWord = rs.getString(5);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPhoneNumber(phoneNumber);
				vo.seteMail(eMail);
				vo.setPassWord(passWord);
				
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
