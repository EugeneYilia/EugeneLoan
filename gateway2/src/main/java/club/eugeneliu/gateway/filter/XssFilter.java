package club.eugeneliu.gateway.filter;

import club.eugeneliu.gateway.wrapper.XssHttpServletRequestWrapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssFilter implements Filter {

    private static boolean IS_INCLUDE_RICH_TEXT = false;
    public List<String> excludes = new ArrayList<>();//排斥，排除

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if(StringUtils.isNotBlank(isIncludeRichText)){
            IS_INCLUDE_RICH_TEXT = Boolean.parseBoolean(isIncludeRichText);
        }

        String temp = filterConfig.getInitParameter("excludes");
        if (temp!=null){
            String[] urls = temp.split(temp);
            if(urls!=null){
                for(String url:urls){
                    excludes.add(url);
                }
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (handleExcludeURL(httpServletRequest, httpServletResponse)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        } else {
            XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(httpServletRequest,IS_INCLUDE_RICH_TEXT);
            filterChain.doFilter(xssHttpServletRequestWrapper,servletResponse);
        }
    }

    private boolean handleExcludeURL(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        } else {
            String url = httpServletRequest.getServletPath();
            for (String exclude : excludes) {
                Pattern pattern = Pattern.compile("^" + exclude);
                Matcher matcher = pattern.matcher(url);
                if (matcher.find()) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void destroy() {

    }

}
