package com.bench.Bench.dao;

import com.bench.bean.S3Favorites;
import com.bench.bean.S3FavoritesExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface S3FavoritesMapper {
    long countByExample(S3FavoritesExample example);

    int deleteByExample(S3FavoritesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Favorites record);

    int insertSelective(S3Favorites record);
    
    @Select("select * from s3_favorites where 1=1 and favoritesid=#{favoritesid} and uid=#{uid}")
    List<S3Favorites> selectByFa(S3Favorites record);
    
    @Delete("delete from s3_favorites where 1=1 and favoritesid=#{favoritesid} and uid=#{uid}")
    int deleteByFa(S3Favorites record);
    
    @Insert("insert into s3_favorites value(null,#{favoritesid},#{uid},now())")
    int insertFav(S3Favorites record);

    List<S3Favorites> selectByExample(S3FavoritesExample example);

    S3Favorites selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Favorites record, @Param("example") S3FavoritesExample example);

    int updateByExample(@Param("record") S3Favorites record, @Param("example") S3FavoritesExample example);

    int updateByPrimaryKeySelective(S3Favorites record);

    int updateByPrimaryKey(S3Favorites record);
}