package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DummyUserService implements UserService{

    private GreetingService greetingService;
    @Autowired
    public DummyUserService (GreetingService greetingService) { this.greetingService = greetingService; }

    @Override
    public void greetAll() {
        List<String> list=Arrays.asList("Elodie","Charles");
        for (int i=0; i<list.size(); i++){
            greetingService.greet(list.get(i));
        }
        System.out.println("Hi,all");
    }
}
