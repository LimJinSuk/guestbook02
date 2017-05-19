package com.jx372.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.guestbook.vo.GuestbookVo;

public class GuestbookDao {
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName( "com.mysql.jdbc.Driver" );

			String url = "jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection( url, "webdb", "webdb" );
		} catch( ClassNotFoundException e ) {
			System.out.println( "JDBC Driver 를 찾을 수 없습니다." );
		}
		
		return conn;
	}
	
	public List<GuestbookVo> getList(){
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs = null;
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql ="select no,name,message,date_format(reg_date,'%Y-%m-%d') from guestbook ORDER BY reg_date DESC";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);//칼럼 인덱스
				String name = rs.getString(2);
				String message = rs.getString(3);
				String regDate = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setRegDate(regDate);
				
				list.add(vo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	//리스트 전부 가져옴
		public boolean insert(GuestbookVo vo){
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				//값이 바인딩 되는 것이라 변수값은 ?로 받아옴
				//아직 완벽한 쿼리가 아님 바인딩 해야함
				String sql="insert "
						+ " into guestbook"
						+ " values(null,?,?,?,now())";
				pstmt = conn.prepareStatement(sql);
				
				//?-바인딩
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getMessage());
				//pstmt.setString(4, vo.getReg_date());
				
				//몇개가 insert되었는지
				int count = pstmt.executeUpdate();
				
				//0일 경우 실패
				return count==1;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally{
					try {
						if(pstmt!=null)
							pstmt.close();
						if(conn!=null)
							conn.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		
		
		public boolean delete( GuestbookVo vo ) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				
				String sql = "delete "
						+ " from guestbook "
						+ " where no = ? and password = ?";
				pstmt = conn.prepareStatement( sql );
				
				pstmt.setLong( 1, vo.getNo() );
				pstmt.setString( 2, vo.getPassword() );
				
				int count = pstmt.executeUpdate();
				
				return count == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					if( pstmt != null ) {
						pstmt.close();
					}
					
					if( conn != null ) {
						conn.close();
					}
				} catch( SQLException e ) {
					e.printStackTrace();
				}
			}
		}
		
	
}
