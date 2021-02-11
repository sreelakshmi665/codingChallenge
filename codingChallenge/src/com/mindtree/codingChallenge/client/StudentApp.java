package com.mindtree.codingChallenge.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mindtree.codingChallenge.entity.ClassroomEntity;
import com.mindtree.codingChallenge.entity.StudentEntity;
import com.mindtree.codingChallenge.exception.StudentException;
import com.mindtree.codingChallenge.exception.serviceException.ServiceException;
import com.mindtree.codingChallenge.exception.serviceException.StudentIdNotFoundException;
import com.mindtree.codingChallenge.service.ServiceInterface;
import com.mindtree.codingChallenge.service.serviceImplementation.StudentService;

public class StudentApp {
	static Scanner scan = new Scanner(System.in);
	static ClassroomEntity classroomEntity = new ClassroomEntity();
	static StudentEntity studentEntity = new StudentEntity();

	public static void main(String[] args) throws Exception {
		List<ClassroomEntity> classroom = new ArrayList<>();
		List<StudentEntity> student = new ArrayList<>();
		ServiceInterface service = new StudentService();
		boolean check = true;
		do {
			options();
			int choice = integerValidate();
			switch (choice) {
			case 1:
				classroomEntity = classroomDetails(classroom);
				try {
					classroom = service.createClassroomDetails(classroomEntity, classroom);
				} catch (ServiceException e) {
					try {
						throw new StudentException(e.getMessage());
					} catch (StudentException exception) {
						System.out.println(exception.getMessage());
					}
				}
				break;
			case 2:
				System.out.println("Enter classroom id");
				int classroomId = integerValidate();
				studentEntity = studentDetails(student);
				try {
					classroom = service.createStudent(studentEntity, classroom, classroomId);
				} catch (StudentIdNotFoundException e) {
					throw new ServiceException(e.getMessage());
				} catch (ServiceException excep) {
					try {
						throw new StudentException(excep.getMessage());
					} catch (StudentException exception) {
						System.out.println(exception.getMessage());
					}
				}

				break;
			case 3:
				System.out.println("enter student id");
				int studentId = integerValidate();
				ClassroomEntity room = new ClassroomEntity();
				try {
					room = service.getStudentDetails(studentId);
				} catch (ServiceException e) {
					try {
						throw new StudentException(e.getMessage());
					} catch (StudentException exception) {
						System.out.println(exception.getMessage());
					}
				}
				catch (StudentException exception) {
					System.out.println(exception.getMessage());
				}
				
					displayStudentDetails(room);
				break;
			case 4:
				System.out.println("enter student id");
				int studentsId = integerValidate();
				System.out.println("Enter age");
				short age = shortValidate();
				try {
					service.updateStudentDetails(studentsId, age);
				} catch (ServiceException e) {
					try {
						throw new StudentException(e.getMessage());
					} catch (StudentException exception) {
						System.out.println(exception.getMessage());
					}
				}
				catch (StudentException exception) {
					System.out.println(exception.getMessage());
				}

				break;
			case 5:
				check = false;
				break;
			}

		} while (check);
	}

	private static void displayStudentDetails(ClassroomEntity room) {

		System.out.println(room);
	}

	private static ClassroomEntity classroomDetails(List<ClassroomEntity> classroom) {
		System.out.println("Enter classroom id");
		int classroomId = integerValidate();
		while (alreadyExists(classroomId, classroom)) {
			System.out.println("classroomId alreadyExists! please try with another");
			classroomId = integerValidate();
		}
		System.out.println("Enter classroom name");
		String classroomName = scan.next();
		System.out.println("Enter total number of students");
		int noOfStudents = integerValidate();
		classroomEntity = new ClassroomEntity(classroomId, classroomName, noOfStudents);
		return classroomEntity;
	}

	private static StudentEntity studentDetails(List<StudentEntity> student) {
		System.out.println("Enter student id");
		int studentId = integerValidate();
		System.out.println("Enter student name");
		String studentName = scan.next() + scan.nextLine();
		System.out.println("Enter student age");
		short age = shortValidate();
		studentEntity = new StudentEntity(studentId, studentName, age);
		return studentEntity;
	}

	private static short shortValidate() {
		short data = 0;
		boolean validate = false;
		while (validate == false) {
			if (scan.hasNextShort()) {
				data = scan.nextShort();
				validate = true;
			} else if (!scan.hasNextShort()) {
				System.out.println("Please Enter a Number in Integer!");
				scan.nextShort();
			}
		}
		return data;
	}

	private static boolean alreadyExists(int classroomId, List<ClassroomEntity> classroom) {
		for (ClassroomEntity value : classroom) {
			if (value.getClassroomId() == classroomId) {
				return true;
			}
		}
		return false;
	}

	public static int integerValidate() {
		int data = 0;
		boolean validate = false;
		while (validate == false) {
			if (scan.hasNextInt()) {
				data = scan.nextInt();
				validate = true;
			} else if (!scan.hasNextInt()) {
				System.out.println("Please Enter a Number in Integer!");
				scan.next();
			}
		}
		return data;
	}

	private static void options() {
		System.out.println("Enter your choice");
		System.out.println("1.create classroom entity\n 2.create students and assign to a classroom\n "
				+ "3.Fetch student and classroom details by giving student id\n 4.update student age by giving studentId \n 5.Exit");
	}
}
