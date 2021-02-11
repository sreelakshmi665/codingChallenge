package com.mindtree.codingChallenge.daoLayer.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.codingChallenge.daoLayer.DaoInterface;
import com.mindtree.codingChallenge.entity.ClassroomEntity;
import com.mindtree.codingChallenge.entity.StudentEntity;
import com.mindtree.codingChallenge.exception.daoException.DBUtillException;
import com.mindtree.codingChallenge.exception.daoException.DaoException;
import com.mindtree.codingChallenge.exception.serviceException.ServiceException;
import com.mindtree.codingChallenge.exception.serviceException.StudentIdNotFoundException;
import com.mindtree.codingChallenge.utility.DBUtill;
public class DaoImplementation implements DaoInterface{
	Connection con;
	@Override
	public void addClassroomDetails(ClassroomEntity classroomEntity) throws Exception {
		try {
			con = DBUtill.getMyConnection();
			Statement st = con.createStatement();
			try {
			String sree = "insert into classroom values(" +classroomEntity.getClassroomId()  + "," + "'" +classroomEntity.getClassroomName()+ "'" + ","  + classroomEntity.getNoOfStudents()+")";
			st.executeUpdate(sree);
			}catch(SQLException e) {
				try {
				throw new DBUtillException("Duplicate Entry");}
				catch(DBUtillException exception) {
					throw new DaoException ("Duplicate Entry");
				}
			}
			DBUtill.closeResource(st);
			DBUtill.closeResource(con);}
			catch(DBUtillException e){throw new DaoException("Connection issue");
			}
		
	}

	@Override
	public void addStudentDetails(StudentEntity studentEntity, int classroomId) throws Exception {
		try {
			con = DBUtill.getMyConnection();
			
			Statement st = con.createStatement();
			try {
			String sree = "insert into student values(" +studentEntity.getStudentId()  + "," + "'" + studentEntity.getStudentName()+ "'" + ","  + studentEntity.getAge()+","+classroomId+")";
			st.executeUpdate(sree);
			}
			catch(SQLException e) {
				try {
					throw new DBUtillException("Duplicate Entry");}
					catch(DBUtillException exception) {
						throw new DaoException ("Duplicate Entry");
					}
				}
			DBUtill.closeResource(st);
			DBUtill.closeResource(con);}
			catch(DBUtillException e){throw new DaoException("Connection issue");}
		
	}

	@Override
	public ClassroomEntity retriveStudentDetails(int studentId) throws Exception {
		ClassroomEntity coll=new ClassroomEntity();
		try {
		con =DBUtill.getMyConnection();
		boolean check=true;
		Statement st = con.createStatement();
		String query="select *from student where studentId="+studentId;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			check=false;
			List<StudentEntity> stu=new ArrayList<>();
			stu.add(new StudentEntity(rs.getInt("studentId"),rs.getString("studentName"),rs.getShort("age")));
			PreparedStatement ps=con.prepareStatement("select classroom.classroomId,classroom.classroomName,classroom.noOfStudents from classroom join student on classroom.classroomId=student.classroomId where classroom.classroomId="+rs.getInt("classroomId")); 
			
			ResultSet rs1=ps.executeQuery();
			while(rs1.next())
			{
				coll=new ClassroomEntity(rs1.getInt("classroomId"),rs1.getString("classroomName"),rs1.getInt("noOfStudents"),stu);
			}
			
			DBUtill.closeResource(ps);
		}
		
		try {
			if(check) {
			throw new StudentIdNotFoundException("No data found");
		}
		}
	catch(StudentIdNotFoundException e) {
		throw new ServiceException("No data found");
	}
		
		DBUtill.closeResource(st);
		DBUtill.closeResource(con);
		
	}
		catch(DBUtillException e){throw new DaoException("Connection issue");}
		return coll;
	}

	@Override
	public void updateStudentDetails(int studentsId,short age) throws Exception {
		try {
		con =DBUtill.getMyConnection();
		boolean check=true;
		Statement st = con.createStatement();
		String query="select *from student where studentId="+studentsId;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			check=false;
			PreparedStatement ps=con.prepareStatement("update student set age="+age+" where studentId="+studentsId); 
			ps.executeUpdate();
			
			DBUtill.closeResource(ps);
		}
		try {
			if(check) {
			throw new StudentIdNotFoundException("No data found");
		}
		}
	catch(StudentIdNotFoundException e) {
		throw new ServiceException("No data found");
	}
		
		DBUtill.closeResource(st);
		DBUtill.closeResource(con);
		
	}
		catch(DBUtillException e){throw new DaoException("Connection issue");
	}

}
}
