package com.websys.springmvc.service;

import java.util.List;

import com.websys.springmvc.model.Student;



public interface StudentService {
	
	Student findById(long id);
	
	Student findByName(String name);
	
	void saveStudent(Student student);
	
	void updateStudent(Student student);
	
	void deleteStudentById(long id);

	List<Student> findAllStudents(); 
	
	void deleteAllStudents();
	
	public boolean isUserExist(Student student);
	
}
