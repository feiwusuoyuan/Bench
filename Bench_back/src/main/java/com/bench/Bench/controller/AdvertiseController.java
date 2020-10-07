package com.bench.Bench.controller;

import com.bench.Bench.dao.AdvertiseDao;
import com.bench.bean.S3Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertiseController {

    @Autowired
    private AdvertiseDao advDao;

    @CrossOrigin
    @GetMapping("api/pushadvertise")
    public void pushadvertise(S3Announcement announcement){
        advDao.save(announcement);
    }

}
