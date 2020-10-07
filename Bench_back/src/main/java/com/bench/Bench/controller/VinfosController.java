package com.bench.Bench.controller;

import com.bench.Bench.service.VinfosService;
import com.bench.Bench.utils.pageUtils;
import com.bench.Bench.utils.pages;
import com.bench.bean.S3Vinfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VinfosController {

    @Autowired
    private VinfosService vinfosService;

    @CrossOrigin
    @GetMapping("api/findcert")
    public Page<S3Vinfos> findcert(){
        pages pages=new pages(0,10, "id","DESC");
        Page<S3Vinfos> vinfos=vinfosService.findAllByStatus("审核中",pageUtils.createPageRequest(pages));
        return vinfos;
    }
}
