package com.bench.Bench.dao;

import com.bench.bean.S3Vinfos;
import com.bench.bean.S3VinfosExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface S3VinfosMapper {
    long countByExample(S3VinfosExample example);

    int deleteByExample(S3VinfosExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Vinfos record);

    int insertSelective(S3Vinfos record);

    List<S3Vinfos> selectByExample(S3VinfosExample example);

    S3Vinfos selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Vinfos record, @Param("example") S3VinfosExample example);

    int updateByExample(@Param("record") S3Vinfos record, @Param("example") S3VinfosExample example);

    int updateByPrimaryKeySelective(S3Vinfos record);

    int updateByPrimaryKey(S3Vinfos record);
    
    @Insert("insert into s3_vinfos values(null,#{vname},"
    		+ "#{vinfo},#{uid},#{status},now(),#{name})")
    int insertinfo(S3Vinfos info);
}