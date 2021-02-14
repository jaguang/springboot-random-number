package com.enigma.api.inventory.models;

import com.enigma.api.inventory.models.validations.Alphabetic;

import javax.validation.constraints.Pattern;

public class UnitSearch extends PageSearch {

//    @Pattern(regexp = "[A-Za-z]+")
    @Alphabetic
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
