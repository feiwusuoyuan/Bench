package com.bench.Bench.dao;

import com.bench.bean.S3Attention;
import com.bench.bean.S3AttentionExample;
import com.bench.bean.S3User;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface S3AttentionMapper {
    long countByExample(S3AttentionExample example);

    int deleteByExample(S3AttentionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Attention record);

    int insertSelective(S3Attention record);

    List<S3Attention> selectByExample(S3AttentionExample example);

    S3Attention selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Attention record, @Param("example") S3AttentionExample example);

    int updateByExample(@Param("record") S3Attention record, @Param("example") S3AttentionExample example);

    int updateByPrimaryKeySelective(S3Attention record);

    int updateByPrimaryKey(S3Attention record);
    
    @Insert("insert into s3_attention values(null,#{author},#{fans})")
    int insertAten(S3Attention aten);
}