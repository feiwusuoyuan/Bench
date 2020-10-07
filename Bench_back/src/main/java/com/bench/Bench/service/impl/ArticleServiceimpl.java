package com.bench.Bench.service.impl;

import com.bench.Bench.dao.ArticleDao;
import com.bench.Bench.service.ArticleService;
import com.bench.bean.S3Article;
import com.bench.bean.S3Comment;
import com.bench.bean.S3User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceimpl implements ArticleService {

    @Autowired
    private ArticleDao adao;

    @Override
    public S3Article save(S3Article article) {
        return adao.save(article);
    }


    @Override
    public List<S3Article> findAllByAid(int id) {

        return adao.findAllByAid( id);
    }

    @Override
    public Page<S3Article> findAll(Pageable pageable) {

        return adao.findAll(pageable);
    }

    @Override
    public Page<S3Article> findAllByStatus(String status, Pageable pageable) {
        Page<S3Article> page= adao.findAllByStatus(status,pageable);
        return page;
    }
}
