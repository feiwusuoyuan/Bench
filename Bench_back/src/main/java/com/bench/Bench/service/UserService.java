package com.bench.Bench.service;

import com.bench.bean.S3User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    //单表查询所有
    Page<S3User> findAll( Pageable pageable);

    //批量添加
    public S3User save(S3User s3User);

    //单个删除
    public void deleteOne(int id);

    //单个修改
    public S3User updata(S3User s3User);

    //根据ID查找
    public Optional<S3User> findOne(int id);
}
