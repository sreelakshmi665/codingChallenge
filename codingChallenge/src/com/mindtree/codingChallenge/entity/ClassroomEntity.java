package com.mindtree.codingChallenge.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassroomEntity {
private int classroomId;
private String classroomName;
private int noOfStudents;
List<StudentEntity> student=new ArrayList<>();
public int getClassroomId() {
	return classroomId;
}
public void setClassroomId(int classroomId) {
	this.classroomId = classroomId;
}
public String getClassroomName() {
	return classroomName;
}
public void setClassroomName(String classroomName) {
	this.classroomName = classroomName;
}
public int getNoOfStudents() {
	return noOfStudents;
}
public void setNoOfStudents(int noOfStudents) {
	this.noOfStudents = noOfStudents;
}
public List<StudentEntity> getStudent() {
	return student;
}
public void setStudent(List<StudentEntity> student) {
	this.student = student;
}
public ClassroomEntity() {
	super();
}
public ClassroomEntity(int classroomId, String classroomName, int noOfStudents) {
	super();
	this.classroomId = classroomId;
	this.classroomName = classroomName;
	this.noOfStudents = noOfStudents;
}
public ClassroomEntity(int classroomId, String classroomName, int noOfStudents, List<StudentEntity> student) {
	super();
	this.classroomId = classroomId;
	this.classroomName = classroomName;
	this.noOfStudents = noOfStudents;
	this.student = student;
}
@Override
public String toString() {
	return "ClassroomEntity [classroomId=" + classroomId + ", classroomName=" + classroomName + ", noOfStudents="
			+ noOfStudents + ", student=" + student + "]";
}

}
