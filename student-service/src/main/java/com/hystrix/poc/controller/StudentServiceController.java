package com.hystrix.poc.controller;

import com.hystrix.poc.dao.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentServiceController {

    private static Map<String, List<Student>> collegeData = new HashMap<String, List<Student>>();

    static {
        collegeData = new HashMap<>();

        List<Student> lst = new ArrayList<>();
        Student std = new Student("Amit", "Mechanical");
        lst.add(std);
        std = new Student("Ramesh", "ECE");
        lst.add(std);

        collegeData.put("topCollege", lst);

        lst = new ArrayList<Student>();
        std = new Student("Rajat", "CS");
        lst.add(std);
        std = new Student("Manish", "TELECOM");
        lst.add(std);

        collegeData.put("medCollege", lst);
    }

    @RequestMapping(value = "/getStudentDetailsForSchool/{collegename}", method = RequestMethod.GET)
    public List<Student> getStudents(@PathVariable String collegename) {

        List<Student> studentList = collegeData.get(collegename);

        if (studentList == null) {
            studentList = new ArrayList<Student>();
            Student std = new Student("Student Not Found", "N/A");
            studentList.add(std);
        }
        return studentList;
    }
}
