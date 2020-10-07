package com.bench.Bench.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class pageUtils {
    //默认的数目
    private final static Integer SIZE=5;
    //默认开始的页数
    private final static Integer PAGE=0;
    //默认排序字段
    private final static String ID="id";

    public static Pageable createPageRequest(pages pages){
        return PageRequest.of(pages.getPage()<=0?PAGE:pages.getPage(),
                pages.getSize()<=0?SIZE: pages.getSize(),
                Sort.by(null!=pages.getDirection()
                        &&!"".equals(pages.getDirection())
                        &&pages.getDirection().equals("desc")? Direction.DESC: Direction.ASC,
                        StringUtils.isEmpty(pages.getSortColumn())?ID:pages.getSortColumn()));
    }
}
