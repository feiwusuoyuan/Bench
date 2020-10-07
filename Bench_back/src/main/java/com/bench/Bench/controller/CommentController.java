package com.bench.Bench.controller;

import com.bench.Bench.service.CommentService;
import com.bench.Bench.utils.pageUtils;
import com.bench.Bench.utils.pages;
import com.bench.bean.S3Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @CrossOrigin
    @GetMapping("api/findcomm")
    public Page<S3Comment> findcomm(){
        pages pages=new pages(0,10, "regtime","DESC");
        Page<S3Comment> comm=commentService.findAll(pageUtils.createPageRequest(pages));
        return comm;
    }
}
