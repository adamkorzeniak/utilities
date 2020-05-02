package com.adamkorzeniak.json.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAddress {
    private EmailType type;
    private String address;
}
