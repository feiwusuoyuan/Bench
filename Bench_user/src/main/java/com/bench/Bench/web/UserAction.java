package com.bench.Bench.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bench.Bench.dao.S3PushMapper;
import com.bench.Bench.dao.S3UserMapper;
import com.bench.bean.Result;
import com.bench.bean.S3Push;
import com.bench.bean.S3PushExample;
import com.bench.bean.S3User;
import com.bench.bean.S3UserExample;

@RestController
@RequestMapping("user")
public class UserAction {

	@Resource
	private S3UserMapper umapper;
	@Resource
	private S3PushMapper pmapper;
	
	//
	@RequestMapping("infoByAccount")
	public S3User infoByAccount(@RequestBody String account) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andAccountEqualTo(account);
		List<S3User> list = umapper.selectByExample(ue);
		return list.get(0);
	}
	
	@RequestMapping("infoById")
	public S3User infoById(@RequestBody String id) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andIdEqualTo(Integer.valueOf(id));
		List<S3User> list = umapper.selectByExample(ue);
		return list.get(0);
	}

	@PostMapping("register")
	public Result register(@RequestBody S3User user) {
		user.setUhead("/images/0.jpg");
		int res=umapper.insertuser(user);
		if(res==1) {
			return new Result(1, "注册成功", user);
		}else {
			return new Result(0, "注册失败", user);
		}	
		
	}
	@PostMapping("checkaccount")
	public Result checkaccount(@RequestBody S3User user) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andAccountEqualTo(user.getAccount());
		List<S3User> list = umapper.selectByExample(ue);
		if(list.isEmpty()) {
			return new Result(1, "true", user);
		}else {
			return new Result(0, "已存在该用户", user);
		}
	}
	

	@PostMapping("logined")
	public Result logined(@RequestBody S3User user) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andAccountEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		List<S3User> list = umapper.selectByExample(ue);
		if (list.isEmpty()) {
			return new Result(0, "登录失败");
		} else {
			return new Result(1, "登录成功", list.get(0));
		}

	}

	// 获取用户信息
	@RequestMapping("getinfo")
	public Result getinfo(@RequestBody String account) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andAccountEqualTo(account);
		List<S3User> list = umapper.selectByExample(ue);

		return new Result(1, "成功", list.get(0));

	}

	@RequestMapping("getid")
	public Result getid(@RequestBody Integer id) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andIdEqualTo(id);
		List<S3User> list = umapper.selectByExample(ue);

		return new Result(1, "成功", list.get(0));

	}
	//vip
	@RequestMapping("vipup")
	public Result vipup(@RequestBody S3User user) {
		int res=umapper.VipUp(user);
		if (res == 1) {
			return new Result(1, "修改成功");
		} else {
			return new Result(0, "修改失败");
		}

	}

	// 修改邮箱，性别，地点，个性签名
	@RequestMapping("update1")
	public Result update1(@RequestBody S3User user) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andIdEqualTo(user.getId());
		int res=umapper.updateinfo(user);
		if (res == 1) {
			return new Result(1, "修改成功");
		} else {
			return new Result(0, "修改失败");
		}

	}
	//查找原密码
	@RequestMapping("repwd")
	public Result repwd(@RequestBody S3User user) {
		S3UserExample ue = new S3UserExample();
		ue.createCriteria().andIdEqualTo(user.getId());
		List<S3User> list = umapper.selectByExample(ue);
		if (list.isEmpty()) {
			return new Result(0, "该用户不存在");
		} else {
			return new Result(1, "true", list.get(0));
		}
	}
	
	@RequestMapping("savepwd")
	public Result savepwd(@RequestBody S3User user) {
		int res=umapper.updatepwd(user);
		if (res==1) {
			return new Result(1,"修改成功");
		} else {
			return new Result(0,"修改失败");
		}
	}
	
	@PostMapping("updateimg")
	public Result updateimg(@RequestBody S3User user) {
		int res=umapper.updateimg(user);
		if (res==1) {
			return new Result(1,"修改成功");
		} else {
			return new Result(0,"修改失败");
		}
	}
	
	@PostMapping("emailPwd")
	public Result emailPwd(@RequestBody S3User user) {
		int res=umapper.Emailpwd(user);
		if (res==1) {
			return new Result(1,"修改成功");
		} else {
			return new Result(0,"修改失败");
		}
	}
	
	@PostMapping("phonePwd")
	public Result phonePwd(@RequestBody S3User user) {
		int res=umapper.Phonepwd(user);
		if (res==1) {
			return new Result(1,"修改成功");
		} else {
			return new Result(0,"修改失败");
		}
	}
	//增加经验
	@RequestMapping("LevelUp")
	public Result LevelUp(@RequestBody S3User user) {
		int res=umapper.LevelUp(user);
		if (res==1) {
			return new Result(1,"成功");
		} else {
			return new Result(0,"失败");
		}
	}
	

}
