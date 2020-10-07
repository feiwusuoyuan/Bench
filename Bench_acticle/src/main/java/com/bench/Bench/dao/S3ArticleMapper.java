package com.bench.Bench.dao;

import com.bench.bean.S3Article;
import com.bench.bean.S3ArticleExample;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface S3ArticleMapper {
	long countByExample(S3ArticleExample example);

	int deleteByExample(S3ArticleExample example);

	int deleteByPrimaryKey(Integer aid);

	int insert(S3Article record);

	int insertSelective(S3Article record);

	@Insert("insert into s3_article values(null,#{categoryid},#{uid},#{atitle},#{acontent},now(),"
			+ "#{isproblem},#{money},0,0,0,'未审核')")
	int insertarticle(S3Article record);

	List<S3Article> selectByExample(S3ArticleExample example);

	S3Article selectByPrimaryKey(Integer aid);

	@Select("select * from s3_article where 1=1 and uid=#{uid}")
	List<S3Article> selectByUid(int uid);

	int updateByExampleSelective(@Param("record") S3Article record, @Param("example") S3ArticleExample example);

	int updateByExample(@Param("record") S3Article record, @Param("example") S3ArticleExample example);

	int updateByPrimaryKeySelective(S3Article record);

	@Update("update s3_article set solve=1 where aid=#{aid}")
	int updateso(int aid);
	
	@Select("select * from s3_article where aid=#{aid}")
	S3Article selectsl(int aid);
	
	int updateByPrimaryKey(S3Article record);
	

	@Select("select * from s3_article where top=1 order by sendtime desc ")
	@Results(id = "rmAm", value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid"),
			@Result(column = "uid", property = "user", one = @One(select = "com.bench.Bench.dao.S3UserMapper.selectById")) })
	public List<S3Article> selectByNew();

	@Select("select * from s3_article where aid =#{aid}")
	@ResultMap("rmAm")
	public S3Article selectByAid(int aid);

	@Select("select * from s3_article order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> selectAll();

	@Select("select * from s3_article order by reply desc ")
	@ResultMap("rmAm")
	public List<S3Article> selectByReply();


	@Select("select * from s3_article where uid=#{uid} order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> selectByAuthor(S3Article atc);

	@Select("select * from s3_article where isproblem=1 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryProblem();

	@Select("select * from s3_article where categoryid=1 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryIt();
	
	@Select("select * from s3_article where categoryid=2 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryLief();
	
	@Select("select * from s3_article where categoryid=3 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryGame();
	
	@Select("select * from s3_article where categoryid=4 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryXW();
	
	@Select("select * from s3_article where categoryid=5 order by aid desc ")
	@ResultMap("rmAm")
	public List<S3Article> queryOther();
	
	@Select("select * from s3_article where isproblem=1 and uid=#{uid} order by aid desc")
	List<S3Article> selectIiproblem(int uid);

	@Select("SELECT\n" +
			"	cnt,b.aid,atitle,sendtime,isproblem,money,uid\n" +
			"FROM\n" +
			"	(\n" +
			"		SELECT\n" +
			"			count(*) AS cnt,\n" +
			"			aid\n" +
			"		FROM\n" +
			"			s3_comment\n" +
			"		GROUP BY\n" +
			"			aid\n" +
			"		ORDER BY\n" +
			"			cnt DESC\n" +
			"	) AS a\n" +
			"RIGHT JOIN s3_article b ON a.aid = b.aid\n" +
			"ORDER BY a.cnt desc")
	@ResultMap("rmAm")
	List<S3Article> selectByHot();
	
	@Select("SELECT\n" +
			"	cnt,b.aid,atitle,sendtime,isproblem,money,uid\n" +
			"FROM\n" +
			"	(\n" +
			"		SELECT\n" +
			"			count(*) AS cnt,\n" +
			"			aid\n" +
			"		FROM\n" +
			"			s3_comment\n" +
			"		GROUP BY\n" +
			"			aid\n" +
			"		ORDER BY\n" +
			"			cnt DESC\n" +
			"	) AS a\n" +
			"RIGHT JOIN s3_article b ON a.aid = b.aid\n" +
			"ORDER BY a.cnt desc limit 0,6")
	@ResultMap("rmAm")
	List<S3Article> selectByHot1();
}