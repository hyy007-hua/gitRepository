package com.chzu.enums;

/**
 * @Desc Excel文件类型
 */
public enum ExcelFileType {
    teachers(0,"教师信息表"),
    students(1,"学生信息表"),
    readError(404,"格式错误");

    public final Integer type;
    public final String value;
    ExcelFileType(Integer type,String value){
        this.type = type;
        this.value  = value;
    }
}
