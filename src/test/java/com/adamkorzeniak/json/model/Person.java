package com.adamkorzeniak.json.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Person {
    private String name;
    private String lastName;
    private int age;
    private Address address;
    private List<EmailAddress> emailAddresses;
}
