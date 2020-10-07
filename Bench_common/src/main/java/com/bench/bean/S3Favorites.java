package com.bench.bean;

import java.util.Date;

public class S3Favorites implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer favoritesid;

    private Integer uid;

    private Date regtime;
    

    private S3Article art;
    
    

    public S3Article getArt() {
		return art;
	}

	public void setArt(S3Article art) {
		this.art = art;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFavoritesid() {
        return favoritesid;
    }

    public void setFavoritesid(Integer favoritesid) {
        this.favoritesid = favoritesid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}