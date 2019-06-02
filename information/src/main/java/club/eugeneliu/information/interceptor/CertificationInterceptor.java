package club.eugeneliu.information.interceptor;//package club.eugeneliu.gateway.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CertificationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/information/all/register")) {//对于特定的资源文件不进行拦截
            return true;
        } else if (request.getRequestURI().startsWith("/information/all/sendCheckCode")) {
            return true;
        } else if (request.getRequestURI().startsWith("/information/all/checkPhoneNumber")) {
            return true;
        } else if (request.getRequestURI().startsWith("/information/all/checkCheckCode")) {
            return true;
        } else if (request.getRequestURI().startsWith("/information/all/sendCheckCode2")) {
            return true;
        } else if (request.getRequestURI().startsWith("/information/all/checkIdCard")) {
            return true;
        } else {//其他的资源要访问的需要先走一遍拦截器
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id_card")) {
                        if (cookie.getValue() != null) {//cookie中有id_card并且其值不为null，拦截器放行
                            return true;
                        } else {//cookie中有id_card值为null，拦截柱重定向到登录页面
                            response.sendRedirect("/static/login.html");
                            return false;
                        }
                    }
                }
                //有cookies但是无id_card这个cookie
                return false;
            } else {
                //请求中不包含id_card这个cookie，进行重定向，并且拦截下来
                response.sendRedirect("/static/login.html");
                return false;
            }
        }
    }
}