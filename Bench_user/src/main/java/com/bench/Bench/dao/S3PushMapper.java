package com.bench.Bench.dao;

import com.bench.bean.S3Push;
import com.bench.bean.S3PushExample;
import com.bench.bean.S3User;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface S3PushMapper {
    long countByExample(S3PushExample example);

    int deleteByExample(S3PushExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Push record);

    int insertSelective(S3Push record);

    List<S3Push> selectByExample(S3PushExample example);

    S3Push selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Push record, @Param("example") S3PushExample example);

    int updateByExample(@Param("record") S3Push record, @Param("example") S3PushExample example);

    int updateByPrimaryKeySelective(S3Push record);

    int updateByPrimaryKey(S3Push record);
    
    @Insert("insert into s3_push values(null,#{channel},"
    		+ "#{author},#{msg},#{url},now(),"
    		+ "#{uid},#{isread})")
    int insertmsg(S3Push push);
    
    @Update("update s3_push set isread=#{isread} where id=#{id}")
    int updatemsg(S3Push push);
    
    @Update("update s3_push set isread=#{isread} where uid=#{uid}")
    int updateAllmsg(S3Push push);
}