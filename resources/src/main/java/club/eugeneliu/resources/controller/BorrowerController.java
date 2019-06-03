package club.eugeneliu.resources.controller;

import club.eugeneliu.resources.constants.IdentityConstants;
import club.eugeneliu.resources.utils.CertificationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/resources/borrower")
public class BorrowerController {
    @GetMapping("/borrow")
    public String borrow(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {//身份符合
                            return "/borrower/borrow";
                        } else {//身份不符合
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/business_intro")
    public String business_intro(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/business_intro";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/finished_funds_record")
    public String finished_funds_record(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/finished_funds_record";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/fund_account")
    public String fund_account(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/fund_account";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/index_borrower")
    public String index_borrower(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/index_borrower";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/repayment_record")
    public String repayment_record(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/repayment_record";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/special_certification")
    public String special_certification(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/special_certification";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/unfinished_funds_record")
    public String unfinished_funds_record(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/unfinished_funds_record";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }

    @GetMapping("/user_info")
    public String user_info(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_type")) {//找到特定的cookie
                if (cookie.getValue() != null) {//cookie中有user_type并且其值不为null
                    try {
                        if (CertificationUtil.decode(cookie.getValue()).equals(IdentityConstants.BORROWER)) {
                            return "/borrower/user_info";
                        } else {
                            return "/AccessDenied";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {//cookie中有user_type值为null，返回未授权的页面
                    return "/AccessDenied";
                }
            }

        }
        //有cookies但是无user_type这个cookie，进行页面跳转
        //因为开始的拦截器放行，所以有cookie，但是无所需要的cookie文件
        return "/AccessDenied";
    }
}
