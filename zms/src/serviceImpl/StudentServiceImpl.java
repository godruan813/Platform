package serviceImpl;

import java.util.List;

import service.StudentService;
import utils.Page;
import dao.StudentDAO;
import daoImpl.StudentDAOImpl;
import entity.Student;

public class StudentServiceImpl implements StudentService{
	
	private StudentDAO sd;
	
	public StudentDAO getSd() {
		return sd;
	}

	public void setSd(StudentDAO sd) {
		this.sd = sd;
	}

	public Student login(Student student){
		Student stu=sd.queryStudentById(student.getSid());
		if(stu==null){
			return null;
		}
		else {
			return stu;
		}
	}

	public void add(Student student) {
		sd.add(student);
	}
	
	public void update(Student student) {
		sd.update(student);
	}
	
	public void delete(Student student) {
		sd.delete(student);
	}

	public List<Student> getAllStudent() {
		return sd.queryAllStudent();
	}
	
	public Student getStudentById(Student student){
		return sd.queryStudentById(student.getSid());
	}
	
	public int getStudentCount(){
		return sd.queryAllCount();
	}
	
	public int getStudentCount(Student student){
		return sd.queryAllCount(student);
	}
	
	public List<Student> getAllStudentByPage(Page page,Student stu) {
		return sd.queryAllStudentByPage(page, stu);
	}

}
