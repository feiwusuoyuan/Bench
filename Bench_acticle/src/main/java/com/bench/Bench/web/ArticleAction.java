package com.bench.Bench.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bench.Bench.dao.S3AnnouncementMapper;
import com.bench.Bench.dao.S3ArticleMapper;
import com.bench.Bench.dao.S3CategoryMapper;
import com.bench.Bench.dao.S3CommentMapper;
import com.bench.Bench.dao.S3FavoritesMapper;
import com.bench.Bench.dao.S3UserMapper;
import com.bench.Bench.dao.S3WebtalkMapper;
import com.bench.bean.Result;
import com.bench.bean.S3Announcement;
import com.bench.bean.S3AnnouncementExample;
import com.bench.bean.S3Article;
import com.bench.bean.S3ArticleExample;
import com.bench.bean.S3Category;
import com.bench.bean.S3Comment;
import com.bench.bean.S3CommentExample;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3Webtalk;

@RestController
@RequestMapping("article")

public class ArticleAction {

	@Resource
	private S3ArticleMapper am;

	@Resource
	private S3FavoritesMapper fm;

	@Resource
	private S3AnnouncementMapper annou;

	@Resource
	private S3CategoryMapper gm;

	@Resource
	private S3CommentMapper cm;
	
	@Resource
	private S3WebtalkMapper wm;


	// 发布文章
	@PostMapping("addarticle")

	public Result addarticle(@RequestBody S3Article a) {
		am.insertarticle(a);
		return new Result(1, "发布成功", a);

	}

	// 首页展示所有置顶文章
	@GetMapping("allarticle")
	public List<S3Article> allarticle() {
		// List<S3Article> list=am.selectByUid(uid);
		List<S3Article> list = am.selectByNew();
		return list;
	}

	// 首页展示文章
	@GetMapping("allart")
	public List<S3Article> allart() {
		// List<S3Article> list=am.selectByUid(uid);
		List<S3Article> list = am.selectAll();
		return list;
	}

	// 首页按热议排列
	@GetMapping("selectByReply")
	public List<S3Article> selectByReply() {
		List<S3Article> list = am.selectByReply();
		return list;
	}

	// 用户发帖
	@RequestMapping("selectuid")
	public List<S3Article> selectuid(@RequestBody S3Article atc) {
		// S3ArticleExample ae = new S3ArticleExample();
		// ae.createCriteria().andUidEqualTo(atc.getUid());
		List<S3Article> list = am.selectByAuthor(atc);
		return list;
	}

	// 用户发帖总数
	@RequestMapping("countuid")
	public long countuid(@RequestBody S3Article atc) {
		S3ArticleExample ae = new S3ArticleExample();
		ae.createCriteria().andUidEqualTo(atc.getUid());
		long res = am.countByExample(ae);
		return res;
	}

	// 用户删帖
	@RequestMapping("delaid")
	public Result delaid(@RequestBody S3Article atc) {
		S3ArticleExample ae = new S3ArticleExample();
		ae.createCriteria().andAidEqualTo(atc.getAid());
		long res = am.deleteByExample(ae);
		if (res == 1) {
			return new Result(1, "删除成功");
		} else {
			return new Result(1, "删除失败");
		}

	}

	// 文章详情
	@RequestMapping("toarticle")
	public S3Article toarticle(@RequestBody int id) {
		S3Article a = am.selectByAid(id);
		return a;
	}

	@RequestMapping("collection")
	@ResponseBody
	public Result collection(@RequestBody S3Favorites fa) {
		fm.insertFav(fa);
		return new Result(1, "收藏成功");
	}

	@RequestMapping("selectcate")
	@ResponseBody
	public Result selectcate(@RequestBody S3Favorites fa) {
		List<S3Favorites> list = fm.selectByFa(fa);

		if (list.size() > 0) {
			return new Result(1, "有此收藏");
		} else {
			return new Result(0, "无此收藏");
		}

	}

	@RequestMapping("cancelcate")
	@ResponseBody
	public Result cancelcate(@RequestBody S3Favorites fa) {
		int yes = fm.deleteByFa(fa);
		return new Result(1, "已取消");

	}

	// 查找广告
	@RequestMapping("selectAn")
	public S3Announcement selectAn() {
		List<S3Announcement> list = annou.select();
		return list.get(0);
	}
	// 查找广告
		@RequestMapping("countAn")
		public int countAn() {
			int res = annou.count();
			return res;
		}

	// 删除广告
	@RequestMapping("deleteAn")
	public Result deleteAn(@RequestBody int id) {
		int res = annou.delete(id);
		if (res == 1) {
			return new Result(1, "");
		} else {
			return new Result(0, "");
		}
	}

	// 查找我发的最新文章
	@RequestMapping("selectByUidNew")
	public S3Article selectByUidNew(@RequestBody S3Article atc) {
		List<S3Article> list = am.selectByAuthor(atc);
		return list.get(0);
	}

	@RequestMapping("selectByAid")
	public S3Article selectByAid(@RequestBody int aid) {
		S3Article atcs = am.selectByAid(aid);
		return atcs;
	}

	// 查找分类返还给页面
	@RequestMapping("querycate")
	public List<S3Category> querycate() {
		List<S3Category> list = gm.selectAll();
		return list;
	}

	@RequestMapping("cateByname")
	public int cateByname(@RequestBody S3Category cate) {
		int cid = gm.selectBycatename(cate);
		return cid;
	}

	// 查找提问
	@RequestMapping("queryProblem")
	public List<S3Article> queryProblem() {
		List<S3Article> list = am.queryProblem();
		return list;
	}

	// 查找技术
	@RequestMapping("queryIt")
	public List<S3Article> queryIt() {
		List<S3Article> list = am.queryIt();
		return list;
	}

	// 查找生活
	@RequestMapping("queryLief")
	public List<S3Article> queryLief() {
		List<S3Article> list = am.queryLief();
		return list;
	}

	// 查找游戏
	@RequestMapping("queryGame")
	public List<S3Article> queryGame() {
		List<S3Article> list = am.queryGame();
		return list;
	}

	// 查找时政
	@RequestMapping("queryXW")
	public List<S3Article> queryXW() {
		List<S3Article> list = am.queryXW();
		return list;
	}

	// 查找其他
	@RequestMapping("queryOther")
	public List<S3Article> queryOther() {
		List<S3Article> list = am.queryOther();
		return list;
	}

	// 评论
	@PostMapping("comm")
	public Result comm(@RequestBody S3Comment comment) {

		cm.insertComment(comment);

		return new Result(1, "评论成功");
	}

	// 查询回复
	@RequestMapping("allcom")
	public List<S3Comment> allcom(@RequestBody int aid) {
		List<S3Comment> list = cm.selectByAid(aid);
		return list;
	}

	// 回复榜单
	@RequestMapping("comdesc")
	public List<S3Comment> comdesc() {
		List<S3Comment> list = cm.selectCount();
		return list;
	}

	// 用户回复
	@RequestMapping("Usercom")
	public List<S3Comment> Usercom(@RequestBody int uid) {
		List<S3Comment> list = cm.selectByUid(uid);
		return list;
	}

	// 回复总数
	@RequestMapping("countcom")
	public long countcom(@RequestBody int aid) {
		S3CommentExample ce = new S3CommentExample();
		ce.createCriteria().andAidEqualTo(aid);
		long res = cm.countByExample(ce);
		return res;
	}

	// 查询最近提问
	@RequestMapping("selectIiproblem")
	public List<S3Article> selectIiproblem(@RequestBody int uid) {
		List<S3Article> list = am.selectIiproblem(uid);
		return list;
	}

	// hot
	@GetMapping("byhot")
	public List<S3Article> byhot() {
		List<S3Article> list = am.selectByHot();
		return list;
	}

	// hot1
	@GetMapping("byhot1")
	public List<S3Article> byhot1() {
		List<S3Article> list = am.selectByHot1();
		return list;
	}
	
	@RequestMapping("webtalk")
	public List<S3Webtalk> webtalk() {
		List<S3Webtalk> list =wm.selectAll();
		return list;
	}
	
	@RequestMapping("recomm")
	public Result recomm(@RequestBody S3Comment s3comment) {
		
		cm.insertComment(s3comment);
		return new Result(1, "评论成功");
	}
	
	//点赞
	@RequestMapping("zan")
	public Result zan(@RequestBody int id) {
		
		S3Comment sc=cm.selectByPrimaryKey(id);
		int a=sc.getZan()+1;
		sc.setZan(a);
		cm.updatazan(sc);
		return new Result(1, "成功");
	}
	@RequestMapping("czan")
	public Result czan(@RequestBody int id) {
		
		S3Comment sc=cm.selectByPrimaryKey(id);
		if(sc.getZan()>0) {
			sc.setZan(sc.getZan()-1);
			cm.updatazan(sc);
		}
		
		return new Result(1, "取消成功");
	}
	
	@RequestMapping("cn")
	public Result cn(@RequestBody int id) {
		cm.updatacn(id);
		return new Result(1, "取消成功");
	}
	
	@RequestMapping("scn")
	public Result scn(@RequestBody int aid) {
        S3Article a=am.selectsl(aid);
        if(a.getSolve()==1) {
        	return new Result(0, "已解决");
        }
        else {
        	am.updateso(aid);
    		return new Result(1, "正好解决");
        }
	}
}
