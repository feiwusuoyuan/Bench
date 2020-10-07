package com.bench.Bench.dao;

import com.bench.bean.S3User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<S3User,Integer> {
    //通过Page、Pageable分页查询出需要返回的数据
    Page<S3User> findAll( Pageable pageable);

}
