package com.hystrix.poc.controller;

import com.hystrix.poc.delegate.StudentServiceDelegate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollegeServiceController {

    @Autowired
    StudentServiceDelegate studentServiceDelegate;

    @RequestMapping(value = "/getSchoolDetails/{collegename}", method = RequestMethod.GET)
    @HystrixCommand
    public String getStudents(@PathVariable String collegename) {

        System.out.println("Going to call student service to get data!");
        return studentServiceDelegate.callStudentServiceAndGetData(collegename);
    }

}
