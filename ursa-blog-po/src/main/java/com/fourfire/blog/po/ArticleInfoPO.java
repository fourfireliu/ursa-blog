package com.fourfire.blog.po;

import java.io.Serializable;
import java.util.Date;

public class ArticleInfoPO implements Serializable {
    private long id;

    private int typeId;

    private String title;

    private String author;

    private Integer readCount;

    private Integer commentCount;

    private String ip;

    private Date createGmtDate;

    private Date modifyGmtDate;

    private String content;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateGmtDate() {
        return createGmtDate;
    }

    public void setCreateGmtDate(Date createGmtDate) {
        this.createGmtDate = createGmtDate;
    }

    public Date getModifyGmtDate() {
        return modifyGmtDate;
    }

    public void setModifyGmtDate(Date modifyGmtDate) {
        this.modifyGmtDate = modifyGmtDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}