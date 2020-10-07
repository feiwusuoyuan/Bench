package com.bench.Bench.controller;

import com.bench.Bench.service.UserService;
import com.bench.Bench.utils.pageUtils;
import com.bench.Bench.utils.pages;
import com.bench.bean.S3User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("api/finduser/{page}/{size}")
    public Page<S3User> finduser(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        pages pages=new pages(page-1,size, "regtime","DESC");
        Page<S3User> users=userService.findAll(pageUtils.createPageRequest(pages));
        return users;
    }

    @CrossOrigin
    @GetMapping("/findandsortPageable/{page}/{size}/{sortType}/{sortableFields}")
    public Page<S3User> findandsortPageable(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @PathVariable("sortType") String sortType,
            @PathVariable("sortableFields")String sortableFields){
        String sort=!("ascending".equals(sortType))?"desc":"asc";
        pages pages=new pages(page-1,size, sortableFields,sort);
        Page<S3User> users=userService.findAll(pageUtils.createPageRequest(pages));
        return users;
    }


}
