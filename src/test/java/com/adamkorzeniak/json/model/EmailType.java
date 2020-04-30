package com.adamkorzeniak.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EmailType {
    @JsonProperty("private")
    PRIVATE,
    @JsonProperty("work")
    WORK,
    @JsonProperty("spam")
    SPAM;

}
