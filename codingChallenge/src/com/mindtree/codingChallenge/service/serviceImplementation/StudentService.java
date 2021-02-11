package com.mindtree.codingChallenge.service.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.codingChallenge.daoLayer.DaoInterface;
import com.mindtree.codingChallenge.daoLayer.daoImplementation.DaoImplementation;
import com.mindtree.codingChallenge.entity.ClassroomEntity;
import com.mindtree.codingChallenge.entity.StudentEntity;
import com.mindtree.codingChallenge.exception.StudentException;
import com.mindtree.codingChallenge.exception.daoException.DaoException;
import com.mindtree.codingChallenge.exception.serviceException.ClassRoomNotFoundException;
import com.mindtree.codingChallenge.exception.serviceException.ServiceException;
import com.mindtree.codingChallenge.service.ServiceInterface;



public class StudentService implements ServiceInterface{
	DaoInterface dao = new DaoImplementation();

	@Override
	public List<ClassroomEntity> createClassroomDetails(ClassroomEntity classroomEntity,
			List<ClassroomEntity> classroom) throws Exception {
		classroom.add(classroomEntity);
		try {
			dao.addClassroomDetails(classroomEntity);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return classroom;
	}

	@Override
	public void updateStudentDetails(int studentsId,short age) throws Exception {
		List<ClassroomEntity> room=new ArrayList<>();
		try {
			dao.updateStudentDetails(studentsId,age);
		}
		catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		catch(ServiceException ex)
		{
			throw new StudentException(ex.getMessage());
		}
		
	}

	@Override
	public List<ClassroomEntity> createStudent(StudentEntity studentEntity, List<ClassroomEntity> classroom,
			int classroomId) throws Exception {
		boolean check = false;
		for (ClassroomEntity value :classroom ) {
			if (value.getClassroomId() == classroomId) {
				check = true;
				value.getStudent().add(studentEntity);
				try {
					dao.addStudentDetails(studentEntity, classroomId);
				} catch (DaoException e) {
					throw new ServiceException(e.getMessage());
				}

			}
		}
		try {
			if (!check)
				throw new ClassRoomNotFoundException("class room id not found ");
		} catch (ClassRoomNotFoundException e) {
			throw new ServiceException(" class room id not found");
		}
		return classroom;	
	}

	@Override
	public ClassroomEntity getStudentDetails(int studentId) throws Exception {
		ClassroomEntity room=new ClassroomEntity();
		try {
			room=dao.retriveStudentDetails(studentId);
		}
		catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		catch(ServiceException ex)
		{
			throw new StudentException(ex.getMessage());
		}
		return room;
	}

}
