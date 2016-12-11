package irepdata.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gvozd on 11.12.2016.
 */
public class authCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.print("Interceptor - pre-handle");
           String userLogin = (String) request.getSession().getAttribute("LOGGEDIN_USER");
            if(userLogin == null)
            {
                System.out.println("userdata=null");
                response.sendRedirect("/index");
                return false;
            }
        System.out.println(" userData="+userLogin);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
