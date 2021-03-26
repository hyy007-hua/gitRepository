package com.chzu.enums;

public enum FileType {
    excel(0,"excel"),
    word(1,"word"),
    outline(2,"outline"),
    courseExamineForm(3,"courseExamineForm"),
    groupList(4,"groupList");

    public final Integer type;
    public final String value;

    FileType(Integer type,String value){
        this.type = type;
        this.value  = value;
    }
}
