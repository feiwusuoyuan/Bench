package com.bench.Bench.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bench.Bench.remote.IArticleAction;
import com.bench.Bench.remote.IUserAction;
import com.bench.Bench.util.CountUtil;
import com.bench.Bench.util.CreatePhoneNumber;
import com.bench.Bench.util.MD5;
import com.bench.Bench.util.TimeChangeUtil;
import com.bench.bean.Result;
import com.bench.bean.S3Article;
import com.bench.bean.S3Attention;
import com.bench.bean.S3Comment;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3Push;
import com.bench.bean.S3User;
import com.bench.bean.S3Vinfos;

@Controller
@SessionAttributes("loginUser")
@Transactional
public class UserAction {

	@Resource
	private IUserAction iua;
	@Resource
	private IArticleAction iaa;
	@Resource
	private RedisTemplate<String, String> redis;
	
	

	// 点击用户名字跳转用户主页
	@RequestMapping("userindex")
	public String touserindex(Model m) {
		String account = redis.opsForValue().get("uname");
		Result res = iua.getinfo(account);
		if (res.getCode() == 1) {
			m.addAttribute("user", res.getData());
			
		}
		return "html/user/index";

	}
	//查找发帖
	@RequestMapping("queryuid")
	@ResponseBody
	public Result queryuid(Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user=iua.infoByAccount(account);
		S3Article art=new S3Article();
		art.setUid(user.getId());
		
		List<S3Article>list=iaa.selectuid(art);
		
		for(S3Article a:list) {
        	if(redis.opsForValue().get("Bench+"+a.getAid())==null) {
        		a.setReder(0);
        	}else {
        		a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+"+a.getAid())));
        	}
        	long res=iaa.countcom(a.getAid());
        	a.setReply((int) res);
        }
		
		long rut=iaa.countuid(art);
		Map<String,Object>map=new HashMap<>();
		map.put("count", rut);
		map.put("list", list);
		return new Result(1,"",map);
	}
	//查找收藏
	@RequestMapping("queryFav")
	@ResponseBody
	public Result queryFav(Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user=iua.infoByAccount(account);
		S3Favorites fav=new S3Favorites();
		fav.setUid(user.getId());
		List<S3Favorites>list=iua.AllFav(fav);
		long rut=iua.countFav(fav);
		Map<String,Object>map=new HashMap<>();
		map.put("count", rut);
		map.put("list", list);
		return new Result(1,"",map);
	}
	//删帖
	@RequestMapping("delaid")
	@ResponseBody
	public String delaid(@RequestParam("aid")String aid) {
		S3Article atc=new S3Article();
		atc.setAid(Integer.valueOf(aid));
		Result res=iaa.delaid(atc);
		if(res.getCode()==1) {
			return res.getMsg();
		}else {
			return res.getMsg();
		}
	}

	// 点击我的主页跳转页面
	@RequestMapping("userhome")
	public String touserhome(Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user=iua.infoByAccount(account);
		Result res = iua.getinfo(account);
		List<S3Article>list=iaa.selectIiproblem(user.getId());
		if (res.getCode() == 1) {
			m.addAttribute("user", res.getData());
			TimeChangeUtil.change(list);
			for(S3Article a:list) {
	        	if(redis.opsForValue().get("Bench+"+a.getAid())==null) {
	        		a.setReder(0);
	        	}else {
	        		a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+"+a.getAid())));
	        	}
	        	long res1=iaa.countcom(a.getAid());
	        	a.setReply((int) res1);
	        }
			m.addAttribute("list", list);
			
		}
		//最近评论
		List<S3Comment>list1=iaa.Usercom(user.getId());
		TimeChangeUtil.change1(list1);
		for(S3Comment com:list1) {
			S3Article art=iaa.selectByAid(com.getAid());
			com.setTitle(art.getAtitle());
		}
		m.addAttribute("list1", list1);
		return "html/user/home";
	}

	// 基本设置页面
	@RequestMapping("userset")
	public String touserset(Model m) {
		String account = redis.opsForValue().get("uname");
		Result res = iua.getinfo(account);
		if (res.getCode() == 1) {
			m.addAttribute("user", res.getData());
		}
		return "html/user/set";
	}

	// 获取原信息
	@RequestMapping("checkinfo")
	@ResponseBody
	public S3User checkinfo() {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		return user;

	}
	// 修改邮箱，性别，地点，个性签名

	@RequestMapping("update1")
	@ResponseBody
	public Result update(@RequestParam("email") String email, @RequestParam() String account,
			@RequestParam() String address, @RequestParam("signature") String signature) {
		String account1 = redis.opsForValue().get("uname");
		S3User user1 = iua.infoByAccount(account1);
		S3User user = new S3User();
		user.setEmail(email);
		user.setAccount(account);
		user.setAddress(address);
		user.setSignature(signature);
		user.setId(user1.getId());
		Result res = iua.update1(user);
		if (res.getCode() == 1) {
			return new Result(1, "修改成功");
		} else {
			return new Result(0, "修改失败");
		}
	}

	// 获取原密码
	@RequestMapping("repwd")
	@ResponseBody
	public Result checkpwd() {
		Map<String, Object> map = new HashMap<>();
		String account1 = redis.opsForValue().get("uname");
		S3User user1 = iua.infoByAccount(account1);
		S3User user = new S3User();
		user.setId(user1.getId());
		Result res = iua.repwd(user);
		int size = CountUtil.GetSize();
		map.put("size", size);
		map.put("data", res.getData());
		if (res.getCode() == 1) {
			return new Result(1, "true", map);
		} else {
			return new Result(1, "false");
		}
	}

	// 修改新密码
	@RequestMapping("savepwd")
	@ResponseBody
	public Result savepwd(@RequestParam("pwd") String pwd, @RequestParam("repwd") String repwd) {
		if (pwd.equals(repwd) == false) {
			return new Result(0, "两次密码不一致");
		}
		String account1 = redis.opsForValue().get("uname");
		S3User user1 = iua.infoByAccount(account1);
		S3User user = new S3User();
		user.setId(user1.getId());
		String MD5pwd = MD5.Md5(pwd);
		user.setPwd(MD5pwd);
		Result res = iua.savepwd(user);
		if (res.getCode() == 1) {
			return new Result(1, "修改成功");
		} else {
			return new Result(0, "修改失败");
		}
	}

	// 修改头像
	@PostMapping("uploadimg")
	public String upload(@RequestParam("file") MultipartFile file, Model m) throws IllegalStateException, IOException {
		String account = redis.opsForValue().get("uname");
		S3User user1 = iua.infoByAccount(account);
		S3User user = new S3User();
		user.setId(user1.getId());
		String dispath = "E:/img/";
		String spath = "/images/";
		String filename = file.getOriginalFilename();// 文件名
		file.transferTo(new File(dispath + filename));//
		user.setUhead(spath + filename);
		Result res = iua.updateimg(user);
		return touserset(m);

	}

	/**
	 * 我的消息页面
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("usermessage")
	public String tousermessage(Model m) {
		String account = redis.opsForValue().get("uname");
		Result res = iua.getinfo(account);
		if (res.getCode() == 1) {
			m.addAttribute("user", res.getData());
		}
		return "html/user/message";

	}

	@RequestMapping("findmsg")
	@ResponseBody
	public List<S3Push> find() {
		String account = redis.opsForValue().get("uname");
		Result res = iua.getinfo(account);
		if (res.getCode() == 1) {
			S3User user = iua.infoByAccount(account);
			S3Push push = new S3Push();
			push.setUid(user.getId());
			List<S3Push> list = iua.findmsg(push);
			// receive.receiveMessage(send.SendRedisMessage("Benchs", "欢迎加入Bench社区"));
			return list;
		} else {
			return null;
		}
	}

	// 删除消息
	@RequestMapping("delmsg")
	@ResponseBody
	public String delmsg(@RequestParam(value = "id") Integer id) {
		S3Push push = new S3Push();
		push.setId(id);
		Result res = iua.delmsg(push);
		if (res.getCode() == 1) {
			return "删除成功";
		} else {
			return "删除失败";
		}
	}

	// 删除所有消息
	@RequestMapping("delAllmsg")
	@ResponseBody
	public String delAllmsg() {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Push push = new S3Push();
		push.setUid(user.getId());
		Result res = iua.delAllmsg(push);
		return null;
	}

	// 消息已读
	@RequestMapping("updatamsg")
	@ResponseBody
	public String updatamsg(@RequestParam(value = "id") Integer id) {
		S3Push push = new S3Push();
		push.setId(id);
		push.setIsread("1");
		Result res = iua.updatamsg(push);
		if (res.getCode() == 1) {
			return "已读";
		} else {
			return "未读";
		}
	}

	// 一键全读
	@RequestMapping("updataAllmsg")
	@ResponseBody
	public String updataAllmsg() {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Push push = new S3Push();
		push.setUid(user.getId());
		push.setIsread("1");
		Result res = iua.updatAllamsg(push);
		return null;

	}

	// 未读消息个数
	@RequestMapping("queryCount")
	@ResponseBody
	public Object queryCount() {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Push push = new S3Push();
		push.setUid(user.getId());
		push.setIsread("0");
		Result res = iua.selectCountMsg(push);
		if (res.getCode() == 1) {
			return res.getData();
		} else {
			return null;
		}
	}

	/**
	 * 找回密码
	 */
	@RequestMapping("checkVcode")
	@ResponseBody
	public Result checkVcode(@RequestParam("vercode") String vercode, @RequestParam("pwd") String pwd,
			@RequestParam("repwd") String repwd, @RequestParam("email") String email) {
		String scode = redis.opsForValue().get("bvcode");
		if (scode.equals(vercode)) {
			if (pwd.equals(repwd)) {
				S3User user = new S3User();
				String dpwd = MD5.Md5(pwd);
				user.setPwd(dpwd);
				user.setEmail(email);
				iua.emailPwd(user);
				return new Result(1, "修改成功");
			} else {
				return new Result(0, "两次密码不一致");
			}
		} else {
			return new Result(0, "输入验证码不正确");
		}
	}

	// 发送手机验证码
	@RequestMapping("Sendephone")
	@ResponseBody
	public Result sendVcode(@RequestParam(value = "phone") String phone) {
		String code = String.valueOf(CreatePhoneNumber.sendPhoneNumber(phone));
		redis.opsForValue().set("pvcode", code, 10, TimeUnit.MINUTES);
		return new Result(1, "验证码已发送");
	}

	@RequestMapping("checkPhone")
	@ResponseBody
	public Result checkPhone(@RequestParam("pcode") String pcode, @RequestParam("pwd") String pwd,
			@RequestParam("repwd") String repwd, @RequestParam("phone") String phone) {
		String scode = redis.opsForValue().get("pvcode");
		if (scode.equals(pcode)) {
			if (pwd.equals(repwd)) {
				S3User user = new S3User();
				String dpwd = MD5.Md5(pwd);
				user.setPwd(dpwd);
				user.setPhone(phone);
				iua.phonePwd(user);
				return new Result(1, "修改成功");
			} else {
				return new Result(0, "两次密码不一致");
			}
		} else {
			return new Result(0, "输入验证码不正确");
		}
	}

	/**
	 * 用户认证
	 */
	@RequestMapping("insertVinfo")
	@ResponseBody
	public Result insertVinfo(@RequestParam("vname") String vname, @RequestParam("vinfo") String vinfo,
			@RequestParam("name") String name, Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Vinfos info = new S3Vinfos();
		info.setVname(vname);
		info.setVinfo(vinfo);
		info.setUid(user.getId());
		info.setName(name);
		info.setStatus("审核中");
		Result res = iua.insertVinfo(info);
		if (res.getCode() == 1) {
			return new Result(1, "申请成功");
		} else {
			return new Result(0, "");
		}
	}
	/**
	 * 查找认证信息
	 */
	@RequestMapping("selectVinfo")
	@ResponseBody
	public Result selectVinfo(Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Vinfos info=new S3Vinfos();
		info.setUid(user.getId());
		Result res=iua.selectVinfo(info);
		if(res.getCode()==1) {
			return new Result(1,"",res.getData());
		}else {
			return new Result(0,"");
		}
		
	}
	
	/**
	 * 他人用户页面
	 */
	@RequestMapping("toOther")
	public String toOther(@RequestParam("account")String account,Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		
		String account1 = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account1);
		if(account.equals(account1)) {
			return touserhome(m);
		}else {
			m.addAttribute("user", user);
			S3User user1=iua.infoByAccount(account);
			m.addAttribute("otheruser", user1);
			//11111
			List<S3Article>list=iaa.selectIiproblem(user1.getId());
				TimeChangeUtil.change(list);
				for(S3Article a:list) {
		        	if(redis.opsForValue().get("Bench+"+a.getAid())==null) {
		        		a.setReder(0);
		        	}else {
		        		a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+"+a.getAid())));
		        	}
		        	long res1=iaa.countcom(a.getAid());
		        	a.setReply((int) res1);
				m.addAttribute("list", list);
				//最近评论
				List<S3Comment>list1=iaa.Usercom(user.getId());
				TimeChangeUtil.change1(list1);
				for(S3Comment com:list1) {
					S3Article art=iaa.selectByAid(com.getAid());
					com.setTitle(art.getAtitle());
				}
				m.addAttribute("list1", list1);
				
			}
				//11111
			return "html/user/otherhome";
		}
		
		
	}
	
	@RequestMapping("overOther")
	@ResponseBody
	@Transactional
	public Result overOther(@RequestParam("id")String id) {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Attention aten=new S3Attention();
		aten.setAuthor(Integer.valueOf(id));
		aten.setFans(user.getId());
		Result res=iua.overOther(aten);
		if(res.getCode()==1) {
			//fans
			S3Push push=new S3Push();
			S3User user1=iua.infoById(id);
			push.setAuthor(user1.getAccount());
			push.setChannel(user1.getAccount());
			push.setMsg("感谢你的关注，我会经常在Bench社区和大家沟通交流！");
			push.setUid(user.getId());
			push.setIsread("0");
			iua.insertmsg(push);
			//author
			S3Push push1=new S3Push();
			push1.setAuthor("系统消息");
			push1.setChannel(account);
			push1.setMsg("有新的用户关注了你哦");
			push1.setUid(Integer.valueOf(id));
			push1.setIsread("0");
			iua.insertmsg(push1);
			return new Result(1,"");
		}else {
			return new Result(0,"");
		}
		
	}
	
	//判断是否已关注
	@RequestMapping("checkOther")
	@ResponseBody
	public String checkOther(@RequestParam("id")String id,Model m) {
		String account = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(account);
		S3Attention ate=new S3Attention();
		ate.setAuthor(Integer.valueOf(id));
		ate.setFans(user.getId());
		Result res=iua.checkOther(ate);
		if(res.getCode()==1) {
			return res.getMsg();
		}else {
			return res.getMsg();
		}
	}
	/**
	 * 发起会话页面
	 */
	@RequestMapping("chat")
	public String chat(Model m) {
		String account = redis.opsForValue().get("uname");
		Result res = iua.getinfo(account);
		if (res.getCode() == 1) {
			m.addAttribute("user", res.getData());
		}
		return "html/user/chat";
	}
	
	@RequestMapping("querysname")
	@ResponseBody
	public String querysname() {
		String account = redis.opsForValue().get("uname");
		return account;
	}
	

}
