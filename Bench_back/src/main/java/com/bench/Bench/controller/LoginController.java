package com.bench.Bench.controller;

import com.bench.Bench.service.MuserService;
import com.bench.bean.Result;
import com.bench.bean.S3Muser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    MuserService muserService;

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Result mlogin(@RequestBody S3Muser s3Muser, HttpSession session){
        String maccount=s3Muser.getMaccount();
        // 对 html 标签进行转义，防止 XSS 攻击
        maccount= HtmlUtils.htmlEscape(maccount);

        S3Muser muser=muserService.get(maccount,s3Muser.getPwd());
        if (null == muser) {
            return new Result(0, "登录失败,账号或密码错误！");
        } else {
            session.setAttribute("muser",muser);
            return new Result(1, "登录成功");
        }
    }
}
