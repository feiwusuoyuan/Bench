package com.bench.Bench.dao;

import com.bench.bean.S3Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiseDao extends JpaRepository<S3Announcement,Integer> {
}
