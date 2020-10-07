package com.bench.Bench.service;

import com.bench.bean.S3Vinfos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VinfosService {

    Page<S3Vinfos> findAllByStatus(String status, Pageable pageable);

}
