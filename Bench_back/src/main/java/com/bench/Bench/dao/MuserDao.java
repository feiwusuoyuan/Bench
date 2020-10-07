package com.bench.Bench.dao;

import com.bench.bean.S3Muser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuserDao extends JpaRepository<S3Muser,Integer> {
    //查询所有管理员
    List<S3Muser> findAll();

    S3Muser findByMaccount(String Maccount);

    S3Muser findByMaccountAndPwd(String Maccount , String pwd);

}
