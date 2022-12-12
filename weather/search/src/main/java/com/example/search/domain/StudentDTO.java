package com.example.search.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDTO {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
