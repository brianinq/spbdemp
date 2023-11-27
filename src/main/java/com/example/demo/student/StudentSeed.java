package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentSeed {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            var martelle = new Student( "Martelle",  LocalDate.of(2000, Month.APRIL, 8), "martelle@gmail.com");
            var liam = new Student( "Liam",  LocalDate.of(2003, Month.APRIL, 8), "liam@gmail.com");
            var leah = new Student( "Leah",  LocalDate.of(2003, Month.APRIL, 8), "leah@gmail.com");

            studentRepository.saveAll(List.of(martelle, liam, leah));
        };
    }
}
