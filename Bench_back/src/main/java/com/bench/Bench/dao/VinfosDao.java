package com.bench.Bench.dao;

import com.bench.bean.S3Vinfos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinfosDao extends JpaRepository<S3Vinfos,Integer> {

    Page<S3Vinfos> findAllByStatus(String status, Pageable pageable);

    Page<S3Vinfos> countAllByStatus(String status,Pageable pageable);

}

