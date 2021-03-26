package com.chzu.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_file")
public class TbFile {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件类型
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 文件存储地址
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 上传时间
     */
    @Column(name = "up_time")
    private Date upTime;

    /**
     * 上传者的id
     */
    @Column(name = "up_id")
    private String upId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件类型
     *
     * @return file_type - 文件类型
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取文件存储地址
     *
     * @return file_path - 文件存储地址
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件存储地址
     *
     * @param filePath 文件存储地址
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取上传时间
     *
     * @return up_time - 上传时间
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * 设置上传时间
     *
     * @param upTime 上传时间
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    /**
     * 获取上传者的id
     *
     * @return up_id - 上传者的id
     */
    public String getUpId() {
        return upId;
    }

    /**
     * 设置上传者的id
     *
     * @param upId 上传者的id
     */
    public void setUpId(String upId) {
        this.upId = upId;
    }
}