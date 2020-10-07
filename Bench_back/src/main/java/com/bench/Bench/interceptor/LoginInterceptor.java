package com.bench.Bench.interceptor;

import com.bench.bean.S3Muser;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 定义拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        //存放需要被拦截的页面的路径
        String[] requiredAuthPages=new String[ ] {"index"};

        String uri=request.getRequestURI();
        uri = StringUtils.remove(uri,contextPath+"/");
        String page=uri;
        if(begingWith(page,requiredAuthPages)){
            S3Muser muser=(S3Muser)session.getAttribute("muser");
            //如果session存在muser，则放行，否则赶回login页面
            if(muser==null){
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    public boolean begingWith(String page,String[] requiredAuthPages){
        boolean result=false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page,requiredAuthPage)){
                result=true;
                break;
            }
        }
        return result;
    }
}
