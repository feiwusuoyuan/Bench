package com.bench.Bench.dao;

import com.bench.bean.S3Article;
import com.bench.bean.S3ArticleExample;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface S3ArticleMapper {
    long countByExample(S3ArticleExample example);

    int deleteByExample(S3ArticleExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(S3Article record);

    int insertSelective(S3Article record);
    
    @Insert("insert into s3_article values(null,#{categoryid},#{uid},#{atitle},#{acontent},now(),"
    		+ "#{isproblem},#{money},0,0,0,0,0,0)")
    int insertarticle(S3Article record); 
    
    List<S3Article> selectByExample(S3ArticleExample example);

    S3Article selectByPrimaryKey(Integer aid);
    
    @Select("select * from s3_article where 1=1 and uid=#{uid}")
    List<S3Article> selectByUid(int uid);

    int updateByExampleSelective(@Param("record") S3Article record, @Param("example") S3ArticleExample example);

    int updateByExample(@Param("record") S3Article record, @Param("example") S3ArticleExample example);

    int updateByPrimaryKeySelective(S3Article record);

    int updateByPrimaryKey(S3Article record);
    
    @Select("select * from s3_article where top=1 order by sendtime desc ")
	@Results(id = "rmAm",value = {
			@Result(id=true,property = "id",column = "id"),
			@Result(property = "uid",column = "uid"),
			@Result(
					column = "uid",property = "user",
					one = @One(select="com.bench.Bench.dao.S3UserMapper.selectById")
					)
	})
	public List<S3Article> selectByNew();
    
    @Select("select * from s3_article where aid =#{aid}")
	public S3Article selectByAid(S3Article atc);
    
    @Select("select * from s3_article order by aid desc ")
    @ResultMap("rmAm")
	public List<S3Article> selectAll();
    
    @Select("select * from s3_article order by reply desc ")
    @ResultMap("rmAm")
	public List<S3Article> selectByReply();
    
    @Update("update s3_article set reder=reder+1 where aid=#{aid}")
    public int updateReader(int aid);
    
    
}