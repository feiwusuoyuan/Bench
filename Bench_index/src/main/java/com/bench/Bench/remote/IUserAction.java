package com.bench.Bench.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bench.bean.Result;
import com.bench.bean.S3Attention;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3Push;

import com.bench.bean.S3User;
import com.bench.bean.S3Vinfos;

@FeignClient("buser")
public interface IUserAction {
	
	@PostMapping("user/register")
	Result register(@RequestBody S3User user);
	
	@PostMapping("user/checkaccount")
	Result checkaccount(@RequestBody S3User user);
	
	@PostMapping("user/logined")
	Result logined(@RequestBody S3User user);
	
	@PostMapping("user/getinfo")
	Result getinfo(@RequestBody String account);
	
	@PostMapping("user/getid")
	Result getid(@RequestBody Integer id);
	
	@RequestMapping("user/update1")
	Result update1(@RequestBody S3User user);
	
	@RequestMapping("user/repwd")
	Result repwd(@RequestBody S3User user);
	
	@RequestMapping("user/vipup")
	Result vipup(@RequestBody S3User user);
	
	@RequestMapping("user/savepwd")
	Result savepwd(@RequestBody S3User user);
	
	@PostMapping("user/updateimg")
	Result updateimg(@RequestBody S3User user);
	
	@RequestMapping("user/infoByAccount")
	S3User infoByAccount(@RequestBody String account);
	
	@RequestMapping("user/infoById")
	S3User infoById(@RequestBody String id);
	
	@RequestMapping("user/insertmsg")
	Result insertmsg(@RequestBody S3Push push);


	@RequestMapping("user/findmsg")
	List<S3Push> findmsg(@RequestBody S3Push push);
	
	@RequestMapping("user/delmsg")
	Result delmsg(@RequestBody S3Push push);
	
	@RequestMapping("user/delAllmsg")
	Result delAllmsg(@RequestBody S3Push push);
	
	@RequestMapping("user/updatamsg")
	Result updatamsg(@RequestBody S3Push push);
	
	@RequestMapping("user/updatAllamsg")
	Result updatAllamsg(@RequestBody S3Push push);
	
	@RequestMapping("user/selectCountMsg")
	Result selectCountMsg(@RequestBody S3Push push);
	
	@RequestMapping("user/emailPwd")
	Result emailPwd(@RequestBody S3User user);
	
	@RequestMapping("user/phonePwd")
	Result phonePwd(@RequestBody S3User user);
	
	@RequestMapping("user/LevelUp")
	Result LevelUp(@RequestBody S3User user);
	
	@RequestMapping("user/insertVinfo")
	Result insertVinfo(@RequestBody S3Vinfos info);
	
	@RequestMapping("user/countFav")
	long countFav(@RequestBody S3Favorites fav);
	
	@RequestMapping("user/AllFav")
	List<S3Favorites> AllFav(@RequestBody S3Favorites fav);
	
	@RequestMapping("user/selectVinfo")
	Result selectVinfo(@RequestBody S3Vinfos info);
	
	@RequestMapping("user/overOther")
	Result overOther(@RequestBody S3Attention aten);
	
	@RequestMapping("user/checkOther")
	Result checkOther(@RequestBody S3Attention aten);
	
	@RequestMapping("user/FindFans")
	List<S3Attention> FindFans(@RequestBody S3Attention push);
}
