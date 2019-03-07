package com.mrcoder.sbmpmultidbdruid.config;

public enum DBTypeEnum {
    db1("db1"), db2("db2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}