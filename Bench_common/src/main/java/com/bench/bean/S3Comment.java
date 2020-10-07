package com.bench.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "s3_comment")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class S3Comment implements java.io.Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

    private String conent;

    private Integer uid;

    private Integer aid;

    private String isadoption;

    private Date regtime;
    @Transient
    private Integer zan;
    @Transient
    private  S3User user;
    @Transient
    private String TimeChange;
    @Transient
    private String title;
    @Transient
    private Integer cnt;
    @Transient
    private  S3Article atc;
    
    public S3User getUser() {
		return user;
	}

	public void setUser(S3User user) {
		this.user = user;
	}

	public String getTimeChange() {
		return TimeChange;
	}

	public void setTimeChange(String timeChange) {
		TimeChange = timeChange;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public S3Article getAtc() {
		return atc;
	}

	public void setAtc(S3Article atc) {
		this.atc = atc;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent == null ? null : conent.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getIsadoption() {
        return isadoption;
    }

    public void setIsadoption(String isadoption) {
        this.isadoption = isadoption == null ? null : isadoption.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }
}
