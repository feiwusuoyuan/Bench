package com.bench.Bench.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bench.Bench.remote.IArticleAction;
import com.bench.Bench.remote.IUserAction;
import com.bench.Bench.util.CountUtil;
import com.bench.Bench.util.MD5;
import com.bench.Bench.util.SendMail;
import com.bench.Bench.util.TimeChangeUtil;
import com.bench.Bench.util.TimeSumUtil;
import com.bench.bean.Result;
import com.bench.bean.S3Announcement;
import com.bench.bean.S3Article;
import com.bench.bean.S3Category;
import com.bench.bean.S3Comment;
import com.bench.bean.S3Push;

import com.bench.bean.S3User;
import com.bench.bean.S3Webtalk;

import ch.qos.logback.core.util.TimeUtil;
import io.micrometer.core.instrument.util.TimeUtils;

@Controller
@SessionAttributes("loginUser")
@Transactional
public class IndexAction {

	@Resource
	private SendMail sm;
	@Resource
	private RedisTemplate<String, String> redis;
	@Resource
	private IUserAction iua;
	@Resource
	private IArticleAction iaa;

	@RequestMapping("/")
	public String index(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			
		}else {
			S3User user=iua.infoByAccount(redis.opsForValue().get("uname"));
			m.addAttribute("loginUser", user);
		}
		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.allart();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		
		
        int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			// 广告id存入Redis
			redis.opsForValue().set("Bench_anno", String.valueOf(res.getId()), Integer.valueOf(res.getDays()),
					TimeUnit.DAYS);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}
		return "html/index";
	}

	// 从用户页面跳到主页
	@RequestMapping("index")
	public String reindex(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.allart();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		
		
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/index";
	}

	// 按热议排列
	@RequestMapping("ByReply")
	public String ByReply(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		List<S3Article> list1 = iaa.byhot();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);

		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}


		return "html/ByReply";
	}

	@RequestMapping("reg")
	public String toreg() {

		return "html/user/reg";
	}

	@RequestMapping("login")
	public String tologin() {

		return "html/user/login";

	}

	@RequestMapping("tomlogin")
	public String tomlogin() {

		return "html/back/mangerlogin";

	}

	@RequestMapping("forget")
	public String forget() {

		return "html/user/forget";

	}

	@RequestMapping("phoneforget")
	public String phoneforget() {

		return "html/user/phoneforget";

	}

	@RequestMapping("toadd")
	public String tomadd(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);
		List<S3Category> list = iaa.querycate();
		if (list.size() > 0) {
			m.addAttribute("catelist", list);
		}

		return "html/jie/add";

	}

	@RequestMapping("todetail")
	@Transactional
	public String todetail(int aid, Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		
		// 浏览量
		if (redis.opsForValue().get("Bench+" + aid) == null) {
			int h = 1;
			redis.opsForValue().set("Bench+" + aid, String.valueOf(h));
		} else {
			int h = Integer.valueOf(redis.opsForValue().get("Bench+" + aid));
			h++;
			redis.opsForValue().set("Bench+" + aid, String.valueOf(h));
		}
		S3Article a = iaa.toarticle(aid);
		a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
		m.addAttribute("article", a);
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);
		boolean ismy = a.getUser().getAccount().equals(user.getAccount()) ? true : false;
		m.addAttribute("ismy", ismy);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);

		// 回复总数
		long res = iaa.countcom(aid);
		m.addAttribute("countcom", res);

		/**
		 * 广告
		 */
		S3Announcement res1 = iaa.selectAn();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ta = sdf.format(res1.getTime());
		Date now = new Date(System.currentTimeMillis());
		String tnow = sdf.format(now);

		String s = TimeSumUtil.sum(ta, tnow);
		String ss[] = s.split(":");
		if (Integer.valueOf(ss[0]) >= Integer.valueOf(res1.getDays())) {
			// 广告时间到了，删除广告
			String id = redis.opsForValue().get("Bench_anno");
			S3Announcement anno = new S3Announcement();
			anno.setId(Integer.valueOf(id));
			iaa.deleteAn(anno.getId());
			m.addAttribute("announ", "");
			m.addAttribute("count", redis.opsForValue().get("Bench+" + aid));
		} else {
			// 广告
			m.addAttribute("announ", res1);
			m.addAttribute("count", redis.opsForValue().get("Bench+" + aid));
		}
		
		if(a.getIsproblem()==1&&ismy==true) {
			m.addAttribute("ispro",true);
		}else {
			m.addAttribute("ispro",false);
		}


		return "html/jie/detail";
	}

	@RequestMapping("logout")
	public String logout(SessionStatus ss, Model m) {
		// SessionStatus 用于终止会话
		ss.setComplete();
		redis.delete("uname");
		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.allart();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String sss[] = s.split(":");
				if (Integer.valueOf(sss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/index";
	}

	@PostMapping("logined")
	@Transactional
	@ResponseBody
	public ModelAndView tologin(@Valid S3User user, Errors errors, ModelAndView m) {
		CountUtil.CountSize(user.getPwd().length());
		String MD5pwd = MD5.Md5(user.getPwd());
		user.setPwd(MD5pwd);
		Result res = iua.logined(user);
		if (res.getCode() == 1) {
			m.addObject("loginUser", res.getData());
			redis.opsForValue().set("uname", user.getAccount(), 200, TimeUnit.DAYS);//

			List<S3Article> list = iaa.allarticle();
			TimeChangeUtil.change(list);
			for (S3Article a : list) {
				if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
					a.setReder(0);
				} else {
					a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
				}
				long res3 = iaa.countcom(a.getAid());
				a.setReply((int) res3);
			}
			m.addObject("list", list);
			List<S3Article> list1 = iaa.allart();
			TimeChangeUtil.change(list1);
			for (S3Article aa : list1) {
				if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
					aa.setReder(0);
				} else {
					aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
				}
				long res4 = iaa.countcom(aa.getAid());
				aa.setReply((int) res4);
			}
			m.addObject("list1", list1);
			// 公告
			List<S3Webtalk> list4 = iaa.webtalk();
			m.addObject("list4", list4);
			// 回复榜单
			List<S3Comment> list2 = iaa.comdesc();
			m.addObject("list2", list2);
			// 热议
			List<S3Article> list3 = iaa.byhot1();
			TimeChangeUtil.change(list3);
			for (S3Article aa : list3) {
				if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
					aa.setReder(0);
				} else {
					aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
				}
				long res4 = iaa.countcom(aa.getAid());
				aa.setReply((int) res4);
			}
			m.addObject("list3", list3);
			
			int res1=iaa.countAn();
			if(res1!=0) {
				S3Announcement res6 = iaa.selectAn();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ta = sdf.format(res6.getTime());
				Date now = new Date(System.currentTimeMillis());
				String tnow = sdf.format(now);
				String s = TimeSumUtil.sum(ta, tnow);
				String ss[] = s.split(":");
					if (Integer.valueOf(ss[0]) >= Integer.valueOf(res6.getDays())) {
						// 广告时间到了，删除广告
						iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
						m.addObject("announ", "");
					} else {
						// 广告
						m.addObject("announ", res);
					}
			}else {
				m.addObject("announ", null);
			}

			m.setViewName("html/index");
			return m;
		} else {
			m.addObject("info", "用户名或密码错误");
			user.setPwd(MD5pwd.substring(0, CountUtil.GetSize()));
			m.addObject("user", user);
			m.setViewName("html/user/login");
			return m;
		}
	}

	@PostMapping("register")
	@Transactional
	public String reg(@Valid S3User user, Errors errors, Model m) {
		if (user.getRepwd().equals(user.getPwd()) == false) {
			errors.rejectValue("repwd", "repwdError", "两次密码不一致");
		}
		if (errors.hasErrors()) {
			m.addAttribute("user", user);
			m.addAttribute("errors", errors.getFieldErrors());
			return "html/user/reg";
		}
		Result re = iua.checkaccount(user);
		if (re.getCode() == 1) {
			String MD5pwd = MD5.Md5(user.getPwd());
			user.setPwd(MD5pwd);
			Result res = iua.register(user);
			if (res.getCode() == 1) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 查找id 插入系统消息
				S3User user1 = iua.infoByAccount(user.getAccount());
				S3Push push = new S3Push();
				push.setUid(user1.getId());
				push.setChannel("Bench");
				push.setAuthor("系统消息");
				push.setIsread("0");
				push.setMsg("欢迎加入Bench社区");
				push.setRegtime(Date.valueOf(sdf.format(new Date(System.currentTimeMillis()))));
				iua.insertmsg(push);

				List<S3Article> list = iaa.allarticle();
				TimeChangeUtil.change(list);
				for (S3Article a : list) {
					if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
						a.setReder(0);
					} else {
						a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
					}
					long res3 = iaa.countcom(a.getAid());
					a.setReply((int) res3);
				}
				m.addAttribute("list", list);
				List<S3Article> list1 = iaa.allart();
				TimeChangeUtil.change(list1);
				for (S3Article aa : list1) {
					if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
						aa.setReder(0);
					} else {
						aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
					}
					long res4 = iaa.countcom(aa.getAid());
					aa.setReply((int) res4);
				}
				m.addAttribute("list1", list1);
				// 公告
				List<S3Webtalk> list4 = iaa.webtalk();
				m.addAttribute("list4", list4);
				// 回复榜单
				List<S3Comment> list2 = iaa.comdesc();
				m.addAttribute("list2", list2);
				// 热议
				List<S3Article> list3 = iaa.byhot1();
				TimeChangeUtil.change(list3);
				for (S3Article aa : list3) {
					if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
						aa.setReder(0);
					} else {
						aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
					}
					long res4 = iaa.countcom(aa.getAid());
					aa.setReply((int) res4);
				}
				m.addAttribute("list3", list3);
				S3Announcement res2 = iaa.selectAn();
				// 广告
				int res1=iaa.countAn();
				if(res1!=0) {
					S3Announcement res6 = iaa.selectAn();
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String ta = sdf.format(res6.getTime());
					Date now = new Date(System.currentTimeMillis());
					String tnow = sdf.format(now);
					String s = TimeSumUtil.sum(ta, tnow);
					String ss[] = s.split(":");
						if (Integer.valueOf(ss[0]) >= Integer.valueOf(res6.getDays())) {
							// 广告时间到了，删除广告
							iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
							m.addAttribute("announ", "");
						} else {
							// 广告
							m.addAttribute("announ", res);
						}
				}else {
					m.addAttribute("announ", null);
				}

				return "html/index";
			} else {
				m.addAttribute("user", user);
				m.addAttribute("errors", errors.getFieldErrors());
				return "html/user/reg";
			}
		} else {
			errors.rejectValue("account", "accountError", "该用户名已存在");
			m.addAttribute("user", user);
			m.addAttribute("errors", errors.getFieldErrors());
			return "html/user/reg";
		}

	}

	// 发送验证码
	@RequestMapping("Sendemail")
	@ResponseBody
	public Result sendVcode(@RequestParam(value = "email") String email) {
		Random r = new Random();
		int num = 100000 + r.nextInt(899999);
		sm.sendSimpleMail(email, "验证码", "你好，你的验证码为" + num + "，有效期为10分钟，请尽快处理");
		redis.opsForValue().set("bvcode", String.valueOf(num), 10, TimeUnit.MINUTES);
		return new Result(1, "验证码已发送");

	}

	// 提问分类
	@RequestMapping("indexpro")
	public String indexpro(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryProblem();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}


		return "html/indexpro";
	}

	// 技术
	@RequestMapping("indexit")
	public String indexit(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryIt();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/indexit";
	}

	// 生活
	@RequestMapping("indexlief")
	public String indexlief(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryLief();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/indexlief";
	}

	// 游戏
	@RequestMapping("indexgame")
	public String indexgame(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryGame();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/indexgame";
	}

	// 时政
	@RequestMapping("indexXW")
	public String indexXW(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryXW();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/indexXW";
	}

	// 其他
	@RequestMapping("indexOther")
	public String indexOther(Model m) {
		if(redis.opsForValue().get("uname")==null) {
			return "html/user/login";
		}
		S3User user = iua.infoByAccount(redis.opsForValue().get("uname"));
		m.addAttribute("loginUser", user);

		List<S3Article> list = iaa.allarticle();
		TimeChangeUtil.change(list);
		for (S3Article a : list) {
			if (redis.opsForValue().get("Bench+" + a.getAid()) == null) {
				a.setReder(0);
			} else {
				a.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + a.getAid())));
			}
			long res = iaa.countcom(a.getAid());
			a.setReply((int) res);
		}
		m.addAttribute("list", list);
		List<S3Article> list1 = iaa.queryOther();
		TimeChangeUtil.change(list1);
		for (S3Article aa : list1) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list1", list1);
		// 公告
		List<S3Webtalk> list4 = iaa.webtalk();
		m.addAttribute("list4", list4);
		// 回复榜单
		List<S3Comment> list2 = iaa.comdesc();
		m.addAttribute("list2", list2);
		// 热议
		List<S3Article> list3 = iaa.byhot1();
		TimeChangeUtil.change(list3);
		for (S3Article aa : list3) {
			if (redis.opsForValue().get("Bench+" + aa.getAid()) == null) {
				aa.setReder(0);
			} else {
				aa.setReder(Integer.valueOf(redis.opsForValue().get("Bench+" + aa.getAid())));
			}
			long res = iaa.countcom(aa.getAid());
			aa.setReply((int) res);
		}
		m.addAttribute("list3", list3);
		int res1=iaa.countAn();
		if(res1!=0) {
			S3Announcement res = iaa.selectAn();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ta = sdf.format(res.getTime());
			Date now = new Date(System.currentTimeMillis());
			String tnow = sdf.format(now);
			String s = TimeSumUtil.sum(ta, tnow);
			String ss[] = s.split(":");
				if (Integer.valueOf(ss[0]) >= Integer.valueOf(res.getDays())) {
					// 广告时间到了，删除广告
					iaa.deleteAn(Integer.valueOf(redis.opsForValue().get("Bench_anno")));
					m.addAttribute("announ", "");
				} else {
					// 广告
					m.addAttribute("announ", res);
				}
		}else {
			m.addAttribute("announ", null);
		}

		return "html/indexOther";
	}

	

	
	//查询评论
	@GetMapping("cL")
	@ResponseBody
	public Result cl(@RequestParam("aid") int aid) {
	List<S3Comment> list = iaa.allcom(aid);
	if (list.size() > 0) {
		return new Result(1,"有数据",list);
	} else {
		return new Result(0,"无数据",null);
	}
	}
	
	
	
}
