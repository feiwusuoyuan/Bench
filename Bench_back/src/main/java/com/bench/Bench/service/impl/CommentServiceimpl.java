package com.bench.Bench.service.impl;

import com.bench.Bench.dao.CommentDao;
import com.bench.Bench.service.CommentService;
import com.bench.bean.S3Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("CommentServiceimpl")
public class CommentServiceimpl implements CommentService {

    @Autowired
    private CommentDao cdao;

    @Override
    public Page<S3Comment> findAll(Pageable pageable) {
        Page<S3Comment> page= cdao.findAll(pageable);
        return page;
    }
}
