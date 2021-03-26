package com.chzu.model.vo;

/**
 * @Desc 用作构建layui框架中穿梭框所需信息的存储格式
 */
public class TransferData {
    private String title;
    private String value;
    private String isRight;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }
}
