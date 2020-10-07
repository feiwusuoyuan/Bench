package com.bench.Bench.dao;

import com.bench.bean.S3Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<S3Article,Integer> {


    List<S3Article> findAllByAid(int id);

    //@Query使用自定义sql语句，nativeQuery = true表示可以使用本地sql
    @Query(value = "select b.account,a.* from s3_article `a` ,s3_user `b` where a.uid=b.id",nativeQuery = true)
    Page<S3Article> findAll(Pageable pageable);


    Page<S3Article> findAllByStatus(String status,Pageable pageable);

}
