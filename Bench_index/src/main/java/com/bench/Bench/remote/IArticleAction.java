package com.bench.Bench.remote;



import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bench.bean.Result;
import com.bench.bean.S3Announcement;
import com.bench.bean.S3Article;
import com.bench.bean.S3Category;
import com.bench.bean.S3Comment;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3Webtalk;

@FeignClient("barticle")
public interface IArticleAction {
	

		
	@PostMapping("article/addarticle")
	Result addarticle(@RequestBody S3Article a);

	@RequestMapping("article/selectuid")
	List<S3Article> selectuid(@RequestBody S3Article atc);
	
	@RequestMapping("article/countuid")
	long countuid(@RequestBody S3Article atc);
	
	@RequestMapping("article/delaid")
	Result delaid(@RequestBody S3Article atc);
	
	@GetMapping("article/toarticle")
	List<S3Article> toarticle();
	
	@GetMapping("article/allarticle")
	List<S3Article> allarticle();
	
	@GetMapping("article/allart")
	List<S3Article> allart();
	
	@GetMapping("article/selectByReply")
	List<S3Article> selectByReply();
	
	@RequestMapping("article/toarticle")
	S3Article toarticle(@RequestBody int id);
	
	@RequestMapping("article/collection")
	Result collection(@RequestBody S3Favorites fa);
	
	@RequestMapping("article/updateIcr")
	Result updateIcr(@RequestBody int aid);
	
	@RequestMapping("article/selectcate")
	Result selectcate(@RequestBody S3Favorites fa);
	
	@RequestMapping("article/cancelcate")
	Result cancelcate(@RequestBody S3Favorites fa);
	
	@RequestMapping("article/selectAn")
	S3Announcement selectAn();
	
	@RequestMapping("article/countAn")
	int countAn();
	
	@RequestMapping("article/deleteAn")
	Result deleteAn(@RequestBody int id);
	
	@RequestMapping("article/selectByUidNew")
	S3Article selectByUidNew(@RequestBody S3Article atc);
	
	@RequestMapping("article/querycate")
	List<S3Category> querycate();
	
	@RequestMapping("article/cateByname")
	int cateByname(@RequestBody S3Category ca);
	
	@GetMapping("article/queryProblem")
	List<S3Article> queryProblem();
	
	@GetMapping("article/queryOther")
	List<S3Article> queryOther();
	
	@GetMapping("article/queryXW")
	List<S3Article> queryXW();
	
	@GetMapping("article/queryGame")
	List<S3Article> queryGame();
	
	@GetMapping("article/queryLief")
	List<S3Article> queryLief();
	
	@GetMapping("article/queryIt")
	List<S3Article> queryIt();
	@PostMapping("article/comm")
	Result comm(@RequestBody S3Comment co);
	
	@RequestMapping("article/allcom")
    List<S3Comment> allcom(@RequestBody int aid);
	
	@RequestMapping("article/Usercom")
    List<S3Comment> Usercom(@RequestBody int uid);
	
	@RequestMapping("article/comdesc")
    List<S3Comment> comdesc();
	
	@RequestMapping("article/countcom")
    long countcom(@RequestBody int aid);
	
	
	@RequestMapping("article/selectIiproblem")
	List<S3Article> selectIiproblem(@RequestBody int uid);
	
	@RequestMapping("article/selectByAid")
	S3Article selectByAid(@RequestBody int aid);
	
	@RequestMapping("article/byhot")
    List<S3Article> byhot();
	
	@RequestMapping("article/byhot1")
    List<S3Article> byhot1();
	
	
	@RequestMapping("article/webtalk")
	List<S3Webtalk> webtalk();
	
	
	@RequestMapping("article/recomm")
	Result recomm();
	
	@RequestMapping("article/zan")
	Result zan(@RequestBody int cid);
	
	@RequestMapping("article/czan")
	Result czan(@RequestBody int cid);
	
	@RequestMapping("article/cn")
	Result cn(@RequestBody int id);
	
	@RequestMapping("article/scn")
	Result scn(@RequestBody int aid);
	
}
