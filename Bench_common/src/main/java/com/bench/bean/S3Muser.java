package com.bench.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
//表明是实体类
@Entity
//与数据库进行映射连接
@Table(name = "s3_muser")
/**
 * 因为是做前后端分离，而前后端数据交互用的是 json 格式。
 * 那么 User 对象就会被转换为 json 数据。
 * 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate,
 * 在 jpa 工作过程中，就会创造代理类来继承 User ，并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，
 * 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
 */
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class S3Muser implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

    private String maccount;

    private String name;

    private String pwd;

    private String notes;

    private String phone;

    private String email;

    private Date regtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaccount() {
        return maccount;
    }

    public void setMaccount(String maccount) {
        this.maccount = maccount == null ? null : maccount.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}