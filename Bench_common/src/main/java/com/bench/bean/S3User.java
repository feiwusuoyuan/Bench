package com.bench.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "s3_user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class S3User implements java.io.Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/*@JsonIgnoreProperties(value = "S3User")//避免递归死循环
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)*/
	@Transient
	private List<S3Article> s3Article;
	//One To Many' attribute type should be a container

	@NotEmpty(message = "用户名不能为空")
	@Length(min = 2, message = "用户名最少为2个字符")
	private String account;
	@NotEmpty(message = "邮箱不能为空")
	@Email
	private String email;

	private String address;
	@NotEmpty(message = "密码不能为空")
	@Length(min = 6, message = "密码最少为6个字符")
	private String pwd;
	@NotEmpty(message = "电话号码不能为空")
	@Length(min = 11, max = 11, message = "电话号码只能为11个字符")
	private String phone;

	private Integer ulevel;

	private Date regtime;

	private String signature;

	private String urich;

	private String uhead;

	private String uviplevel;

	private String certificainfo;
	@Transient
	@NotEmpty
	private String repwd;

	public List<S3Article> getS3Article() {
		return s3Article;
	}

	public void setS3Article(List<S3Article> s3Article) {
		this.s3Article = s3Article;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getUlevel() {
		return ulevel;
	}

	public void setUlevel(Integer ulevel) {
		this.ulevel = ulevel;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature == null ? null : signature.trim();
	}

	public String getUrich() {
		return urich;
	}

	public void setUrich(String urich) {
		this.urich = urich == null ? null : urich.trim();
	}

	public String getUhead() {
		return uhead;
	}

	public void setUhead(String uhead) {
		this.uhead = uhead == null ? null : uhead.trim();
	}

	public String getUviplevel() {
		return uviplevel;
	}

	public void setUviplevel(String uviplevel) {
		this.uviplevel = uviplevel == null ? null : uviplevel.trim();
	}

	public String getCertificainfo() {
		return certificainfo;
	}

	public void setCertificainfo(String certificainfo) {
		this.certificainfo = certificainfo == null ? null : certificainfo.trim();
	}
}
