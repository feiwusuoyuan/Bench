package com.bench.Bench.dao;

import com.bench.bean.S3Comment;
import com.bench.bean.S3CommentExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface S3CommentMapper {
    long countByExample(S3CommentExample example);

    int deleteByExample(S3CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Comment record);

    int insertSelective(S3Comment record);
    
    @Insert("insert into s3_comment values(null,#{conent},#{uid},#{aid},#{isadoption},now(),0)")
    int insertComment(S3Comment record);
    
    @Select("select * from s3_comment where aid=#{aid} order by regtime desc ")
	@Results(id = "cmrs",value = {
			@Result(id=true,property = "id",column = "id"),
			@Result(property = "uid",column = "uid"),
			@Result(
					column = "uid",property = "user",
					one = @One(select="com.bench.Bench.dao.S3UserMapper.selectById")
					)
	})
    List<S3Comment> selectByAid(int aid);
    
    @Select("select * from s3_comment where uid=#{uid} order by id desc ")
    List<S3Comment> selectByUid(int uid);
    
    @Select("select count(*) as cnt,uid from s3_comment GROUP BY uid order by count(*) desc LIMIT 0,8")
    @ResultMap("cmrs")
    List<S3Comment> selectCount();
    

    List<S3Comment> selectByExample(S3CommentExample example);

    S3Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Comment record, @Param("example") S3CommentExample example);

    int updateByExample(@Param("record") S3Comment record, @Param("example") S3CommentExample example);

    int updateByPrimaryKeySelective(S3Comment record);

    int updateByPrimaryKey(S3Comment record);
    
    @Update("update s3_comment set zan=#{zan} where id=#{id}")
    int updatazan(S3Comment record);
    
    @Update("update s3_comment set isadoption=1 where id=#{id}")
    int updatacn(int id);
    

}