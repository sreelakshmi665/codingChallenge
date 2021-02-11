package com.mindtree.codingChallenge.daoLayer;

import java.util.List;

import com.mindtree.codingChallenge.entity.ClassroomEntity;
import com.mindtree.codingChallenge.entity.StudentEntity;

public interface DaoInterface {

	void addClassroomDetails(ClassroomEntity classroomEntity) throws Exception;

	void addStudentDetails(StudentEntity studentEntity, int classroomId) throws Exception;

	ClassroomEntity retriveStudentDetails(int studentId) throws Exception;

	

	void updateStudentDetails(int studentsId, short age) throws Exception;

}
