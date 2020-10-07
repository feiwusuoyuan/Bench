package com.bench.Bench.service;

import com.bench.bean.S3Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    S3Article save(S3Article article);

    List<S3Article> findAllByAid(int id);

    Page<S3Article> findAll(Pageable pageable);

    Page<S3Article> findAllByStatus(String status,Pageable pageable);
}
