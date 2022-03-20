package daoIMP;

import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	    String sql = "INSERT INTO student (id, name) values (?,?)";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //针对数据库的具体操作
	    try{
	        conn = new DataBaseConnection();
	        
	        pstmt = conn.getConnection().prepareStatement(sql);
	        pstmt.setLong(1,s.getID());
	        //pstmt.setString(1,s.getID());
	        pstmt.setString(2,s.getName());
	  
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        }
	     catch(Exception e){  }
	      }

	    public void update(Student s){
	    	String sql = "UPDATE student SET  name = ? WHERE id=?  ";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();

				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setString(1,s.getName());
				//pstmt.setString(1,s.getID());
				pstmt.setLong(2,s.getID());

				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
	    }

		public void delete(Long iD){
			String sql = "DELETE FROM student  WHERE id=?  ";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();

				pstmt = conn.getConnection().prepareStatement(sql);

				pstmt.setLong(1,iD);

				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }

		}
	    
	    public List findAll(){
			String sql = "select * from student";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			ResultSet rs=null;
			Student s=null;
			List<Student> students=new ArrayList<>();
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();

				pstmt = conn.getConnection().prepareStatement(sql);

				rs=pstmt.executeQuery();

				while (rs.next()){
					long id=rs.getLong("id");
					String name=rs.getString("name");
					s=new Student();
					s.setID(id);
					s.setName(name);
					students.add(s);

				}
				rs.close();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
			finally {
				return students;
			}

		}

		public Student findByID(long iD){
			String sql = "select * from student where id=?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			ResultSet rs=null;
			Student s=null;
			//针对数据库的具体操作
			try{
				conn = new DataBaseConnection();

				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,iD);

				while (rs.next()){
					long id=rs.getLong("id");
					String name=rs.getString("name");
					s=new Student();
					s.setID(id);
					s.setName(name);


				}
				rs.close();
				pstmt.close();
				conn.close();
			}
			catch(Exception e){  }
			finally {
				return s;//返回student
			}
		}

}
