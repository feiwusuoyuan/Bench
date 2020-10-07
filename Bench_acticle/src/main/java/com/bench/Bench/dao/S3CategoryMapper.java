package com.bench.Bench.dao;

import com.bench.bean.S3Category;
import com.bench.bean.S3CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface S3CategoryMapper {
    long countByExample(S3CategoryExample example);

    int deleteByExample(S3CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Category record);

    int insertSelective(S3Category record);

    List<S3Category> selectByExample(S3CategoryExample example);
    
    @Select("select * from s3_category")
    List<S3Category> selectAll();
    
    @Select("select id from s3_category where 1=1 and catename =#{catename}")
    int selectBycatename(S3Category record);

    S3Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Category record, @Param("example") S3CategoryExample example);

    int updateByExample(@Param("record") S3Category record, @Param("example") S3CategoryExample example);

    int updateByPrimaryKeySelective(S3Category record);

    int updateByPrimaryKey(S3Category record);
}