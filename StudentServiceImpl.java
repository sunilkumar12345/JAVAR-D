package com.websys.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websys.springmvc.model.Student;



@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService{

private static final AtomicLong counter = new AtomicLong();
	
	private static List<Student> students;
	
	static{
		students= populateDummyStudents();
	}

	public List<Student> findAllStudents() {
		return students;
	}
	
	public Student findById(long id) {
		for(Student student : students){
			if(student.getId() == id){
				return student;
			}
		}
		return null;
	}
	
	public Student findByName(String name) {
		for(Student student : students){
			if(student.getName().equalsIgnoreCase(name)){
				return student;
			}
		}
		return null;
	}
	
	public void saveStudent(Student student) {
		student.setId(counter.incrementAndGet());
		students.add(student);
	}

	public void updateStudent(Student student) {
		int index = students.indexOf(student);
		students.set(index, student);
	}

	public void deleteStudentById(long id) {
		
		for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
			Student student = iterator.next();
		    if (student.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(Student student) {
		return findByName(student.getName())!=null;
	}
	
	public void deleteAllStudents(){
		students.clear();
	}

	private static List<Student> populateDummyStudents(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(counter.incrementAndGet(),"Sam","MAths", "C"));
		students.add(new Student(counter.incrementAndGet(),"Tom","MAths", "D"));
		students.add(new Student(counter.incrementAndGet(),"Jerome","PHYSIS", "F"));
		students.add(new Student(counter.incrementAndGet(),"Silvia","Chemistry", "A"));
		return students;
	}

	
	

}