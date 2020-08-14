package com.lushwe.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private String firstName, lastName, job, gender;
    private int age;
    private int salary;


}
