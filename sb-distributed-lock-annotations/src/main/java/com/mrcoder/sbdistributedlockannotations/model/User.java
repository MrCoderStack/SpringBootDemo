package com.mrcoder.sbdistributedlockannotations.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = 5271056342318564651L;

    private String name;
    private Long id;

}
