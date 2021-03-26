package com.chzu.enums;

/**
 * @Desc 用户类型 枚举
 */
public enum UserType {
    teacher(0,"教师"),
    student(1,"学生"),
    admin(3,"管理员");

    public final Integer type;
    public final String value;
    UserType(Integer type,String value){
        this.type = type;
        this.value  = value;
    }
}
