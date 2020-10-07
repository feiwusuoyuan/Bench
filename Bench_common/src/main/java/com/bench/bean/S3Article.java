package com.bench.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "s3_article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class S3Article implements java.io.Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
	private Integer aid;

    /**
     * 把S3User 对象的 id 属性作为 uid 进行了查询。
     * JoinColumn(name = "uid")通过uid进行查询
     *
     * cascade对应的级联操作，
     * optional（任选）表示是否接受为空，false不接受。
     * fetch（ 取来） 加载模式，可选的有懒加载、立即加载。配置懒加载之后会有一定问题，需要另行配置，
     */
  /*  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")*/
    @Transient
    private S3User user;

    private Integer categoryid;

    private Integer uid;

    private String atitle;

    private Date sendtime;

    private Integer isproblem;

    private String money;

    private Integer boutique;

    private Integer top;

    private Integer solve;
    @Transient
    private Integer reply;
    @Transient
    private Integer reder;

    private String status;

    private String acontent;
    @Transient
    private String TimeChange;

    public String getTimeChange() {
		return TimeChange;
	}

	public void setTimeChange(String timeChange) {
		TimeChange = timeChange;
	}

	public S3User getUser() {
		return user;
	}

	public void setUser(S3User user) {
		this.user = user;
	}

	public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle == null ? null : atitle.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getIsproblem() {
        return isproblem;
    }

    public void setIsproblem(Integer isproblem) {
        this.isproblem = isproblem;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public Integer getBoutique() {
        return boutique;
    }

    public void setBoutique(Integer boutique) {
        this.boutique = boutique;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getSolve() {
        return solve;
    }

    public void setSolve(Integer solve) {
        this.solve = solve;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getReder() {
        return reder;
    }

    public void setReder(Integer reder) {
        this.reder = reder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent == null ? null : acontent.trim();
    }
}
