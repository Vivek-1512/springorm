package com.spring.orm.springorm;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entity.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
		Student student = new Student(4321, "Vivek Kumar", " Bhopal");

//		Arrays.asList(new Student(421, "Kevin 11", " Bhopal"), new Student(431, "Chaman", " Bhopal"),
//				new Student(432, "Kamal", " Bhopal")).stream().forEach(std -> studentDao.insert(std));
		// studentDao.insert(student);
		Scanner sc = new Scanner(System.in);
		int studentId;
		String studentName;
		String studentCity;
		while (true) {
			menu();
			int input = sc.nextInt();
			sc.nextLine();
			switch (input) {
			case 1:
				System.out.println("Please Add Student details as follow:");
				System.out.print("Student Id:");
				studentId = sc.nextInt();
				sc.nextLine();
				System.out.print("Student Name:");
				studentName = sc.next();
				System.out.print("Student City:");
				studentCity = sc.next();
				studentDao.insert(new Student(studentId, studentName, studentCity));
				System.out.print("Press Enter to Continue>");
				sc.nextLine();
				break;
			case 2:
				studentDao.getAllStudents().stream().forEach(std -> System.out.println(std));
				System.out.print("Press Enter to Continue>");
				sc.nextLine();
				break;
			case 3:
				System.out.print("Please Enter Student ID:");
				studentId = sc.nextInt();
				Student tempStudent = studentDao.getStudentById(studentId);
				if (tempStudent == null) {
					System.out.print("No Student found with the ID:" + studentId);
					System.out.print("Press Enter to Continue>");
					sc.nextLine();
					break;
				}
				System.out.println(tempStudent);
				System.out.print("Press Enter to Continue>");
				sc.nextLine();
				break;
			case 4:
				System.out.print("Please Enter Student ID to Delete:");
				studentId = sc.nextInt();
				studentDao.deleteStudentById(studentId);
				System.out.print("Press Enter to Continue>");
				sc.nextLine();
				break;
			case 5:
				System.out.println("Please Update Student details as follow:");
				System.out.print("Student Id:");
				studentId = sc.nextInt();
				sc.nextLine();
				System.out.println(studentDao.getStudentById(studentId));
				System.out.print("Student Name:");
				studentName = sc.next();
				System.out.print("Student City:");
				studentCity = sc.next();
				studentDao.updateStudent(new Student(studentId, studentName, studentCity));
				System.out.print("Press Enter to Continue>");
				sc.nextLine();
				break;
			default:
				System.out.println("Successfully Exited!");
				return;

			}
		}

	}

	private static void menu() {
		System.out.println("*********************************************************");
		System.out.println("*	Press 1 - Add new Student							*");
		System.out.println("*	Press 2 - Display All Students						*");
		System.out.println("*	Press 3 - Get Student detail by Student ID			*");
		System.out.println("*	Press 4 - Delete Student							*");
		System.out.println("*	Press 5 - Update Student							*");
		System.out.println("*	Press 6 - Exit										*");
		System.out.println("*********************************************************");
		System.out.println("Enter an Option:");
	}
}
