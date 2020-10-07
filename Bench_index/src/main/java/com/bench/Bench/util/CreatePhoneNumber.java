package com.bench.Bench.util;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class CreatePhoneNumber {

	public static Object sendPhoneNumber(String toPhone) {

		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GGFV8GN2z3cfMVYLTCg", "wWHEoxPBM4TgZ2rj13Vd88PCZmYKIz");
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		
		Random ram=new Random();
		Object code=100000+ram.nextInt(899999);
		
		request.putQueryParameter("PhoneNumbers", toPhone);
		request.putQueryParameter("SignName", "天天商城");
		request.putQueryParameter("TemplateCode", "SMS_201651730");
		HashMap<String,Object>map=new HashMap<>();
		map.put("code", code);
		request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return code;
	}

}
