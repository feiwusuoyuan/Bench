package com.bench.Bench.dao;

import com.bench.bean.S3User;
import com.bench.bean.S3UserExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface S3UserMapper {
    long countByExample(S3UserExample example);

    int deleteByExample(S3UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3User record);

    int insertSelective(S3User record);

    List<S3User> selectByExample(S3UserExample example);

    S3User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3User record, @Param("example") S3UserExample example);

    int updateByExample(@Param("record") S3User record, @Param("example") S3UserExample example);

    int updateByPrimaryKeySelective(S3User record);

    int updateByPrimaryKey(S3User record);
    
    @Update("update s3_user set email=#{email},account=#{account}"
    		+ ",address=#{address},signature=#{signature} where id=#{id}")
    int updateinfo(S3User user);
    
    @Update("update s3_user set pwd=#{pwd} where id=#{id}")
    int updatepwd(S3User user);
    
    @Update("update s3_user set uhead=#{uhead} where id=#{id}")
    int updateimg(S3User user);
    @Insert("insert into s3_user values(null,#{account},"
    		+ "#{email},#{address},#{pwd},#{phone},0,now(),"
    		+ "#{signature},0,#{uhead},#{uviplevel},#{certificainfo})")
    int insertuser(S3User user);
    
    @Update("update s3_user set pwd=#{pwd} where email=#{email}")
    int Emailpwd(S3User user);
    
    @Update("update s3_user set pwd=#{pwd} where phone=#{phone}")
    int Phonepwd(S3User user);
    
    @Update("select * from s3_user where id=#{id}")
    S3User selectById(S3User user);
}