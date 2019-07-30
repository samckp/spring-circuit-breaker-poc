package com.hystrix.poc.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class StudentServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callStudentService_Fallback")
    public String callStudentServiceAndGetData(String collegename) {

        System.out.println("Getting School details for " + collegename);

        String response = restTemplate
                .exchange("http://localhost:8088/getStudentDetailsForSchool/{collegename}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, collegename).getBody();

        System.out.println("Response Received as " + response + " -  " + new Date());

        return "NORMAL FLOW !!! - college Name -  " + collegename + " :::  " +
                " Student Details " + response + " -  " + new Date();
    }

    @SuppressWarnings("unused")
    private String callStudentService_Fallback(String schoolname) {

        System.out.println("Student Service is down!!! fallback route enabled...");

        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                " Service will be back shortly - " + new Date();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
