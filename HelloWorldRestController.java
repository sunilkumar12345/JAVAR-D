package com.websys.springmvc.controller;
 
import java.util.List;
 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 


import com.websys.springmvc.model.Student;

import com.websys.springmvc.service.StudentService;
 
@RestController
public class HelloWorldRestController {
 
    @Autowired
    StudentService studentService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/student/", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudent() {
        List<Student> student = studentService.findAllStudents();
        if(student.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single sTUDENT--------------------------------------------------------
     
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
 
     
   
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody Student student ,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + student.getName());
 
        if (studentService.isUserExist(student)) {
            System.out.println("A User with name " + student.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        studentService.saveStudent(student);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        System.out.println("Updating User " + id);
         
        Student currentStudent = studentService.findById(id);
         
        if (currentStudent==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
 
        currentStudent.setName(student.getName());
        currentStudent.setClasse(student.getClasse());
        currentStudent.setSection(student.getSection());
         
        studentService.updateStudent(currentStudent);
        return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
 
        studentService.deleteStudentById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/student/", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteAllStudents() {
        System.out.println("Deleting All Users");
 
        studentService.deleteAllStudents();
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
 
}