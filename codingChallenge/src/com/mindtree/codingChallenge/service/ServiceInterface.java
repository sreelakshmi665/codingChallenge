package com.mindtree.codingChallenge.service;

import java.util.List;

import com.mindtree.codingChallenge.entity.ClassroomEntity;
import com.mindtree.codingChallenge.entity.StudentEntity;

public interface ServiceInterface {

	List<ClassroomEntity> createClassroomDetails(ClassroomEntity classroomEntity, List<ClassroomEntity> classroom) throws Exception;

	List<ClassroomEntity> createStudent(StudentEntity studentEntity, List<ClassroomEntity> classroom, int classroomId) throws Exception;

	ClassroomEntity getStudentDetails(int studentId) throws Exception;

	void updateStudentDetails(int studentsId, short age) throws Exception;

}
