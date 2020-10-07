package com.bench.Bench.controller;

import com.bench.Bench.service.ArticleService;
import com.bench.Bench.utils.pages;
import com.bench.Bench.utils.pageUtils;
import com.bench.bean.S3Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @CrossOrigin
    @GetMapping("api/articles")
    public Page<S3Article> findArticles(){
        pages pages=new pages(0,5, "sendtime","DESC");
        Page<S3Article> page=articleService.findAll(pageUtils.createPageRequest(pages));
        //String jsonstr = JSONObject.toJSONString((Map<String, ? extends Object>) page);
        return page;
    }

    @CrossOrigin
    @GetMapping("api/findarticle")
    public Page<S3Article> findarticlesByStatus(){
        pages pages=new pages(0,10, "sendtime","DESC");
        Page<S3Article> page=articleService.findAllByStatus("已审核",pageUtils.createPageRequest(pages));
        return page;
    }

    @CrossOrigin
    @GetMapping("api/findarticle2")
    public Page<S3Article> findarticlesByStatus2(){
        pages pages=new pages(0,10, "sendtime","DESC");
        Page<S3Article> page=articleService.findAllByStatus("未审核",pageUtils.createPageRequest(pages));
        return page;
    }

}
