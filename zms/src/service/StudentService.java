package service;

import java.util.List;

import utils.Page;

import entity.Student;

public interface StudentService {

	Student login(Student student);
	void add(Student student);
	void update(Student student);
	void delete(Student student);
	Student getStudentById(Student student);
	List<Student> getAllStudent();
	List<Student> getAllStudentByPage(Page page,Student stu);
	int getStudentCount();
	int getStudentCount(Student student);


}
