package com.mrcoder.sbmannotations.domain;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;

@Data
public class Person extends DataEntity<Integer> {
    private String name;
    private Integer age;
}
