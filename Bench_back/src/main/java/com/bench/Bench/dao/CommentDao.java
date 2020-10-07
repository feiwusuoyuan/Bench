package com.bench.Bench.dao;

import com.bench.bean.S3Comment;
import com.bench.bean.S3Vinfos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<S3Comment,Integer> {

    Page<S3Comment> findAll(Pageable pageable);
}
