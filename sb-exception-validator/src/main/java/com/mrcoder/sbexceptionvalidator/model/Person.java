package com.mrcoder.sbexceptionvalidator.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Person {

    @NotNull(message = "id非空")
    private Integer id;
    @NotBlank(message = "name非空")
    private String name;
}
