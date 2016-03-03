package dao;

import java.util.List;

import utils.Page;

import entity.Student;

public interface StudentDAO {

	Student queryStudentById(String id);
	List<Student> queryAllStudent();
	void add(Student stu);
	void update(Student stu);
	void delete(Student stu);
	List<Student> queryAllStudentByPage(final Page page,final Student stu);
	int queryAllCount();
	int queryAllCount(Student student);

}
