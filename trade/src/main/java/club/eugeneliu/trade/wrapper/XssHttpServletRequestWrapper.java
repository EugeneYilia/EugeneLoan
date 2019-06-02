package club.eugeneliu.trade.wrapper;//package club.eugeneliu.gateway.wrapper;

import club.eugeneliu.trade.utils.JsoupUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest originRequest = null;
    private boolean isIncludeRichText = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        this.originRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getParameter(String name) {
        boolean flag = (("content".equals(name) || name.endsWith("WithHtml")));
        if (flag && !isIncludeRichText) {
            return super.getParameter(name);
        }
        name = JsoupUtil.clean(name);
        String value = super.getParameter(name);//Jsoup对属性名字就进行清理
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);//Jsoup对值进行清理
        }

        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arrs = super.getParameterValues(name);
        if (arrs != null) {
            for (String arr : arrs) {
                arr = JsoupUtil.clean(arr);
            }
        }
        return arrs;
    }

    @Override
    public String getHeader(String name) {
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);
        if(StringUtils.isNotBlank(value)){
            value = JsoupUtil.clean(value);
        }
        return value;
    }

    public HttpServletRequest getOriginRequest() {
        return originRequest;
    }

    public static HttpServletRequest getOriginalRequest(HttpServletRequest httpServletRequest){
        if(httpServletRequest instanceof XssHttpServletRequestWrapper){
            return (XssHttpServletRequestWrapper)((XssHttpServletRequestWrapper) httpServletRequest).getOriginRequest();
        } else {
            return httpServletRequest;
        }
    }
}
