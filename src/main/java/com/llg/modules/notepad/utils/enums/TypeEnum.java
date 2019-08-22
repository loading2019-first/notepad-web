package com.llg.modules.notepad.utils.enums;

public enum TypeEnum {
    INCOME(1,"收入"),
    EXPEND(2,"支出");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TypeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }
}
