package com.bench.Bench.service.impl;

import com.bench.Bench.dao.VinfosDao;
import com.bench.Bench.service.VinfosService;
import com.bench.bean.S3User;
import com.bench.bean.S3Vinfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("VinfosServiceimpl")
public class VinfosServiceimpl implements VinfosService {

    @Autowired
    private VinfosDao vdao;

    @Override
    public Page<S3Vinfos> findAllByStatus(String status, Pageable pageable) {
        Page<S3Vinfos> page= vdao.findAllByStatus(status,pageable);
        return page;
    }
}
