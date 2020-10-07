package com.bench.Bench.dao;

import com.bench.bean.S3Article;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3FavoritesExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface S3FavoritesMapper {
    long countByExample(S3FavoritesExample example);

    int deleteByExample(S3FavoritesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(S3Favorites record);

    int insertSelective(S3Favorites record);

    List<S3Favorites> selectByExample(S3FavoritesExample example);

    S3Favorites selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") S3Favorites record, @Param("example") S3FavoritesExample example);

    int updateByExample(@Param("record") S3Favorites record, @Param("example") S3FavoritesExample example);

    int updateByPrimaryKeySelective(S3Favorites record);

    int updateByPrimaryKey(S3Favorites record);
    
    @Select("select * from s3_favorites where uid=#{uid} order by id desc ")
	@Results(id = "fam",value = {
			@Result(id=true,property = "id",column = "id"),
			@Result(property = "favoritesid",column = "favoritesid"),
			@Result(
					column = "favoritesid",property = "art",
					one = @One(select="com.bench.Bench.dao.S3ArticleMapper.selectByAid")
					)
	})
	public List<S3Favorites> selectByAll(S3Favorites fav);
    
    @Insert("insert into s3_favorites value(null,#{favoritesid},#{uid},now())")
    int insertFav(S3Favorites fav);
}