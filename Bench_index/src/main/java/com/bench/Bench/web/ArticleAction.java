package com.bench.Bench.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bench.Bench.remote.IArticleAction;
import com.bench.Bench.remote.IUserAction;
import com.bench.bean.Result;
import com.bench.bean.S3Article;
import com.bench.bean.S3Attention;
import com.bench.bean.S3Category;
import com.bench.bean.S3Comment;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3Push;
import com.bench.bean.S3User;

import io.lettuce.core.dynamic.domain.Timeout;

@Controller
@SessionAttributes("loginUser")
@Transactional
public class ArticleAction {
	@Resource
	private IArticleAction iaa;
	@Resource
	private IUserAction iua;
	@Resource
	private RedisTemplate<String, String> redis;

//	@Resource
//	private RedisTemplate<String, Integer> redis1; 
	
	S3Favorites fa;
	S3Category cg;
	S3Comment cm;

	// 发表文章
	@PostMapping("addarticle")
	@Transactional
	public String addarticle(Model m, S3Article a, @RequestParam("atitle") String atitle,
			@RequestParam("catename") String catename, @RequestParam("content-editormd-markdown-doc") String acontent,
			@RequestParam("isproblem") int isproblem) {
		String re = redis.opsForValue().get("uname");
		S3User user = iua.infoByAccount(re);
		a.setAtitle(atitle);
		a.setAcontent(acontent);
		a.setIsproblem(isproblem);
		if (isproblem == 1) {
			a.setMoney("10");
		}
		cg = new S3Category();
		cg.setCatename(catename);
		int cid = iaa.cateByname(cg);
		a.setCategoryid(cid);
		a.setUid(user.getId());
		Result res = iaa.addarticle(a);

		if (res.getCode() == 1) {
			// 获取文章id
			S3Article Art = new S3Article();
			Art.setUid(user.getId());
			S3Article atc = iaa.selectByUidNew(Art);
			// 增加经验
			S3User user1 = new S3User();
			user1.setId(user.getId());
			iua.LevelUp(user1);
			//判断vip等级
			S3User user2=new S3User();
			if(user.getUlevel()+10<200 &&user.getUlevel()+10>=100) {
                   user2.setUviplevel("vip1");
                   user2.setId(user.getId());
                   iua.vipup(user2);
			}else if(user.getUlevel()+10<300 &&user.getUlevel()+10>=200){
				user2.setUviplevel("vip2");
				user2.setId(user.getId());
                iua.vipup(user2);
			}else if(user.getUlevel()+10<400 &&user.getUlevel()+10>=300){
				user2.setUviplevel("vip3");
				user2.setId(user.getId());
                iua.vipup(user2);
			}else if(user.getUlevel()+10<500 &&user.getUlevel()+10>=400){
				user2.setUviplevel("vip4");
				user2.setId(user.getId());
                iua.vipup(user2);
			}else if(user.getUlevel()+10>=500){
				user2.setId(user.getId());
				user2.setUviplevel("vip5");
                iua.vipup(user2);
			}
			// 发消息
			S3Attention aten = new S3Attention();
			aten.setAuthor(user.getId());
			List<S3Attention> list = iua.FindFans(aten);
			S3Push push = new S3Push();
			for (S3Attention ate : list) {
				push.setChannel(re);
				push.setAuthor(re);
				push.setMsg("我最新发表了" + atitle + ",快来看一看吧");
				push.setUrl("todetail?aid=" + atc.getAid());
				push.setUid(ate.getFans());
				push.setIsread("0");
				iua.insertmsg(push);
			}
			return "redirect:/";
		} else {
			return "html/user/login";
		}
	}

	@RequestMapping("toindex")
	public String reindex(Model m) {
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);
		List<S3Article> list = iaa.allarticle();
		m.addAttribute("list", list);
		return "html/index";
	}

	@RequestMapping("collection")
	@ResponseBody
	public Result collection(@RequestParam("aid") int aid, @RequestParam("account") String account) {

		S3User u = iua.infoByAccount(account);
		fa = new S3Favorites();
		fa.setFavoritesid(aid);
		fa.setUid(u.getId());
		Result res = iaa.collection(fa);
		return res;
	}

	@RequestMapping("selectcate")
	@ResponseBody
	public Result selectcate(@RequestParam("aid") int aid, @RequestParam("account") String account) {

		S3User u = iua.infoByAccount(account);
		fa = new S3Favorites();
		fa.setFavoritesid(aid);
		fa.setUid(u.getId());
		Result res = iaa.selectcate(fa);
		return res;
	}

	@RequestMapping("cancelcate")
	@ResponseBody
	public Result cancelcate(@RequestParam("aid") int aid, @RequestParam("account") String account) {

		S3User u = iua.infoByAccount(account);
		fa = new S3Favorites();
		fa.setFavoritesid(aid);
		fa.setUid(u.getId());
		Result res = iaa.cancelcate(fa);
		return res;
	}

	// 评论
	@PostMapping("comm")
	public String comm(@RequestParam("content") String content, @RequestParam("account") String account,
			@RequestParam("aid") int aid) {
		S3User u = iua.infoByAccount(account);
		cm = new S3Comment();
		cm.setConent(content);
		cm.setUid(u.getId());
		cm.setAid(aid);
		iaa.comm(cm);
		// 消息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		S3Push push = new S3Push();
		S3Article a = iaa.toarticle(aid);
		push.setUid(a.getUid());
		push.setChannel("Bench");
		push.setAuthor("系统消息");
		push.setIsread("0");
		push.setUrl("todetail?aid=" + aid);
		push.setMsg("有人 评论你了，快去看看吧");
		push.setRegtime(Date.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
		iua.insertmsg(push);
		return "redirect:/todetail?aid="+aid;
	}

	@RequestMapping("recomm")
	@ResponseBody
	public Result recomm(@RequestParam("content") String content, @RequestParam("account") String account,
			@RequestParam("aid") int aid,@RequestParam("who") String who) {
		  cm = new S3Comment(); S3User u = iua.infoByAccount(account);
		  cm.setUid(u.getId()); 
		  cm.setAid(aid); 
		  cm.setConent(content); 
		  Result res=iaa.comm(cm);
		  if(res.getCode()==1) {
			// 消息
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				S3Push push = new S3Push();
				S3Article a = iaa.toarticle(aid);
				push.setUid(u.getId());
				push.setChannel("Bench");
				push.setAuthor("系统消息");
				push.setIsread("0");
				push.setUrl("todetail?aid=" + aid);
				push.setMsg("有人回复你了，快去看看吧");
				push.setRegtime(Date.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				iua.insertmsg(push);

			  return res;
		  }
		  else {
			  return new Result(0,"失败");
		  }
	}
	
	@RequestMapping("zok")
	@ResponseBody
	public Result zan( @RequestParam("id") int id) {
		String account = redis.opsForValue().get("uname");
		boolean iszan=redis.opsForValue().get(account+id) == null?false:true;
		if(iszan) {
			redis.delete(account+id);
			iaa.czan(id);
			return new Result(0,"已取消");
		}
		else {
			redis.opsForValue().set(account+id,"1");
			iaa.zan(id);
			return new Result(1,"已点赞");
			
		} 
		
	}
	
	
	@RequestMapping("cn")
	@ResponseBody
	public Result cn( @RequestParam("id") int id, @RequestParam("aid") int aid) {
		
		Result res=iaa.scn(aid);
		
		if(res.getCode()==1) {
			iaa.cn(id);
			return new Result(1,"ok");
		}	
		return  res;
	}

}
