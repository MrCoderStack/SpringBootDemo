package com.mrcoder.sbminterceptor.model;

import com.mrcoder.sbminterceptor.aop.EncryptDecryptClass;
import com.mrcoder.sbminterceptor.aop.EncryptDecryptField;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@EncryptDecryptClass
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Integer grade;
    @EncryptDecryptField
    private String phone;
}