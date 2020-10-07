package com.bench.Bench.service;

import com.bench.bean.S3Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<S3Comment> findAll( Pageable pageable);
}
