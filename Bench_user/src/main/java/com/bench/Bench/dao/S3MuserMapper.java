package com.bench.Bench.dao;

import com.bench.bean.S3Muser;
import com.bench.bean.S3MuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface S3MuserMapper {
    long countByExample(S3MuserExample example);

    int deleteByExample(S3MuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Muser record);

    int insertSelective(S3Muser record);

    List<S3Muser> selectByExample(S3MuserExample example);

    S3Muser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Muser record, @Param("example") S3MuserExample example);

    int updateByExample(@Param("record") S3Muser record, @Param("example") S3MuserExample example);

    int updateByPrimaryKeySelective(S3Muser record);

    int updateByPrimaryKey(S3Muser record);
}