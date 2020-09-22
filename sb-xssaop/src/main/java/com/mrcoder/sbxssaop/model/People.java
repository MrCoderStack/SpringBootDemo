package com.mrcoder.sbxssaop.model;

import com.mrcoder.sbxssaop.config.annotation.XssField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class People {

    @XssField
    private String name;

    @XssField
    private String info;

    private String des;
}
