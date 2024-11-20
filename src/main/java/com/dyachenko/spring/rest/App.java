package com.dyachenko.spring.rest;

import com.dyachenko.spring.rest.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication comm = context.getBean("communication", Communication.class);

        HttpHeaders headers = comm.getHeaders();
        System.out.println(comm.saveUser(headers) + comm.updateUser(headers, comm.getUserByID(headers, 3L))
                + comm.deleteUser(headers, comm.getUserByID(headers, 3L)));


    }
}
