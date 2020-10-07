package com.bench.Bench.dao;

import com.bench.bean.S3Webtalk;
import com.bench.bean.S3WebtalkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface S3WebtalkMapper {
    long countByExample(S3WebtalkExample example);

    int deleteByExample(S3WebtalkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Webtalk record);

    int insertSelective(S3Webtalk record);
    
    @Select("select * from s3_webtalk order by id desc")
    List<S3Webtalk> selectAll();

    List<S3Webtalk> selectByExample(S3WebtalkExample example);

    S3Webtalk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Webtalk record, @Param("example") S3WebtalkExample example);

    int updateByExample(@Param("record") S3Webtalk record, @Param("example") S3WebtalkExample example);

    int updateByPrimaryKeySelective(S3Webtalk record);

    int updateByPrimaryKey(S3Webtalk record);
}