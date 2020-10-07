package com.bench.Bench.service;

import com.bench.Bench.dao.MuserDao;
import com.bench.bean.S3Muser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MuserService {
    /**
     * 这里实际上是对 MuserDAO 进行了二次封装，
     * 一般来讲，我们在 DAO 中只定义基础的增删改查操作，而具体的操作，需要由 Service 来完成。
     * 当然，由于我们做的操作原本就比较简单，所以这里看起来只是简单地重命名了一下，
     * 比如把 “通过用户名及密码查询并获得对象” 这种方法命名为 get。
     */
    //注入
    @Autowired
    MuserDao mudao;

    public boolean isExist(String maccount){
        S3Muser s3Muser = getByName(maccount);
        return  null!=s3Muser;
    }

    public S3Muser getByName(String maccount){
        return mudao.findByMaccount(maccount);
    }

    public S3Muser get(String maccount,String pwd){
        System.out.println(mudao.findByMaccountAndPwd(maccount, pwd));
        return mudao.findByMaccountAndPwd(maccount, pwd);
    }

    public void add(S3Muser s3Muser){
        mudao.save(s3Muser);
    }
}
