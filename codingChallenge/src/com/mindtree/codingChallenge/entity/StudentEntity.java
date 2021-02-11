package com.mindtree.codingChallenge.entity;

public class StudentEntity {
private int studentId;
private String studentName;
private short age;
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public short getAge() {
	return age;
}
public void setAge(short age) {
	this.age = age;
}

public StudentEntity() {
	super();
}
public StudentEntity(int studentId, String studentName, short age) {
	super();
	this.studentId = studentId;
	this.studentName = studentName;
	this.age = age;
}
@Override
public String toString() {
	return "StudentEntity [studentId=" + studentId + ", studentName=" + studentName + ", age=" + age + "]";
}

}
