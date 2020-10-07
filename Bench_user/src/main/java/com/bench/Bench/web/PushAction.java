package com.bench.Bench.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bench.Bench.dao.S3AttentionMapper;
import com.bench.Bench.dao.S3FavoritesMapper;
import com.bench.Bench.dao.S3PushMapper;
import com.bench.Bench.dao.S3VinfosMapper;
import com.bench.bean.Result;
import com.bench.bean.S3Attention;
import com.bench.bean.S3AttentionExample;
import com.bench.bean.S3Favorites;
import com.bench.bean.S3FavoritesExample;
import com.bench.bean.S3Push;
import com.bench.bean.S3PushExample;
import com.bench.bean.S3Vinfos;
import com.bench.bean.S3VinfosExample;

@RestController
@RequestMapping("user")
public class PushAction {

	@Resource
	private S3PushMapper pmapper;
	
	@Resource
	private S3VinfosMapper vmapper;
	
	@Resource
	private S3AttentionMapper amapper;
	
	@Resource
	private S3FavoritesMapper fmapper;

	@RequestMapping("insertmsg")
	public Result findCh(@RequestBody S3Push push) {
		int res=pmapper.insertmsg(push);
		if (res==1) {
			return new Result(1, "新增成功");
		} else {
			return new Result(0, "修改失败");
		}
	}
	
	@RequestMapping("findmsg")
	public List<S3Push> findmsg(@RequestBody S3Push push) {
		S3PushExample pe=new S3PushExample();
		pe.createCriteria().andUidEqualTo(push.getUid());
		List<S3Push>list=pmapper.selectByExample(pe);
		if (list.isEmpty()==false) {
			return list;
		} else {
			return null;
		}
	}
	
	@RequestMapping("delmsg")
	public Result delmsg(@RequestBody S3Push push) {
		S3PushExample pe=new S3PushExample();
		pe.createCriteria().andIdEqualTo(push.getId());
		int res=pmapper.deleteByExample(pe);
	    return new Result(1,"删除成功");
	}
	
	@RequestMapping("delAllmsg")
	public Result delAllmsg(@RequestBody S3Push push) {
		S3PushExample pe=new S3PushExample();
		pe.createCriteria().andUidEqualTo(push.getUid());
		int res=pmapper.deleteByExample(pe);
	    return new Result(1,"删除成功");
	}

	@RequestMapping("updatamsg")
	public Result updatamsg(@RequestBody S3Push push) {
		int res = pmapper.updatemsg(push);
		if (res==1) {
			return new Result(1, "已读");
		} else {
			return new Result(0, "失败");
		}
	}
	
	@RequestMapping("updatAllamsg")
	public Result updatAllamsg(@RequestBody S3Push push) {
		int res = pmapper.updateAllmsg(push);
		if (res==1) {
			return new Result(1, "全部已读");
		} else {
			return new Result(0, "失败");
		}
	}
	
	@RequestMapping("selectCountMsg")
	public Result selectCountMsg(@RequestBody S3Push push) {
		S3PushExample pe=new S3PushExample();
		pe.createCriteria().andIsreadEqualTo(push.getIsread()).andUidEqualTo(push.getUid());
		long res=pmapper.countByExample(pe);
		if (res>=0) {
			return new Result(1, "",res);
		} else {
			return new Result(0, "失败");
		}
	}
	
	@RequestMapping("insertVinfo")
	public Result insertVinfo(@RequestBody S3Vinfos info) {
		int res=vmapper.insertinfo(info);
		if (res==1) {
			return new Result(1, "",res);
		} else {
			return new Result(0, "失败");
		}
	}
	
	@RequestMapping("selectVinfo")
	public Result selectVinfo(@RequestBody S3Vinfos info) {
		S3VinfosExample ve=new S3VinfosExample();
		ve.createCriteria().andUidEqualTo(info.getUid());
		List<S3Vinfos> list=vmapper.selectByExample(ve);
		if (list.isEmpty()==false) {
			return new Result(1,"成功",list.get(0));
		} else {
			return new Result(0,"失败");
		}
	}
	/**
	 * 关注用户
	 */
	@RequestMapping("overOther")
	public Result overOther(@RequestBody S3Attention aten) {
	  int res=amapper.insertAten(aten);
		if (res==1) {
			return new Result(1,"关注成功");
		} else {
			return new Result(0,"关注失败");
		}
	}
	//判断是否已关注
	@RequestMapping("checkOther")
	public Result checkOther(@RequestBody S3Attention aten) {
		S3AttentionExample ate=new S3AttentionExample();
		ate.createCriteria().andAuthorEqualTo(aten.getAuthor()).andFansEqualTo(aten.getFans());
	  List<S3Attention>list=amapper.selectByExample(ate);
		if (list.isEmpty()==false) {
			return new Result(1,"已关注",list.get(0));
		} else {
			return new Result(0,"未关注");
		}
	}
	//查找收藏帖子总数
	@RequestMapping("countFav")
	public long countFav(@RequestBody S3Favorites fav) {
		S3FavoritesExample fave=new S3FavoritesExample();
		fave.createCriteria().andUidEqualTo(fav.getUid());
		long res=fmapper.countByExample(fave);
		return res;
	}
	//查找收藏帖子
	@RequestMapping("AllFav")
	public List<S3Favorites> AllFav(@RequestBody S3Favorites fav) {
		List<S3Favorites> list=fmapper.selectByAll(fav);
		return list;
	}
	//查找我的粉丝
	@RequestMapping("FindFans")
	public List<S3Attention> FindFans(@RequestBody S3Attention aten) {
		S3AttentionExample ate=new S3AttentionExample();
		ate.createCriteria().andAuthorEqualTo(aten.getAuthor());
		List<S3Attention> list=amapper.selectByExample(ate);
		return list;
	}

}
