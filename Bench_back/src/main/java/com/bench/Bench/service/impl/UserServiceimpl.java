package com.bench.Bench.service.impl;

import com.bench.Bench.dao.UserDao;
import com.bench.Bench.service.UserService;
import com.bench.bean.S3User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UserServiceimpl")
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDao udao;


    @Override
    public Page<S3User> findAll( Pageable pageable) {
        Page<S3User> page= udao.findAll(pageable);
        return page;
    }

    @Override
    @Transactional
    public S3User save(S3User s3User) {
        return udao.save(s3User);
    }

    @Override
    public void deleteOne(int id) {
        udao.deleteById(id);
    }

    @Override
    public S3User updata(S3User s3User) {
        return udao.save(s3User);
    }

    @Override
    public Optional<S3User> findOne(int id) {
        return udao.findById(id);
    }
}
