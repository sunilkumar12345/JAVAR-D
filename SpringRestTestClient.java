package com.websystique.springmvc;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
 

import org.springframework.web.client.RestTemplate;
 

import com.websys.springmvc.model.Student;

 
public class SpringRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4MVCCRUDRestService";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllStudents(){
        System.out.println("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/student/", List.class);
         
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("classe")+", Salary="+map.get("Section"));;
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET */
    private static void getStudents(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        Student user = restTemplate.getForObject(REST_SERVICE_URI+"/student/1", Student.class);
        System.out.println(user);
    }
     
    /* POST */
    private static void createStudents() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        Student user = new Student(0,"Sarah","BILOGY","D");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/student/", user, Student.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateStudent() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        Student user  = new Student(1,"Tomy","ENGG", "E");
        restTemplate.put(REST_SERVICE_URI+"/student/1", user);
        System.out.println(user);
    }
 
    /* DELETE */
    private static void deleteStudent() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/student/3");
    }
 
 
    /* DELETE */
    private static void deleteAllStudent() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/student/");
    }
 
    public static void main(String args[]){
        listAllStudents();
        getStudents();
        createStudents();
        listAllStudents();
        updateStudent();
        listAllStudents();
        deleteStudent();
        listAllStudents();
        deleteAllStudent();
        listAllStudents();
    }
}