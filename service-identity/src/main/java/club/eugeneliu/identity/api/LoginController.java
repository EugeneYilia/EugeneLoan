package club.eugeneliu.identity.api;

import club.eugeneliu.identity.entity.User_required_info;
import club.eugeneliu.identity.service.IUser_required_infoService;
import club.eugeneliu.identity.utils.CertificationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("登录控制器")
@Controller
@RequestMapping("/identity")
public class LoginController {

    @Resource(name = "UserRequiredInfoServiceImpl1")
    private IUser_required_infoService iUser_required_infoService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone_num", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @ApiOperation(value = "验证用户信息", notes = "验证用户的身份，如果登陆成功，生成session，之后这个模块作为身份验证服务,成功就跳转到首页否则返回json告诉登录失败")
    @PostMapping(value = "/all/login")
    public String userLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String phoneNumber = httpServletRequest.getParameter("phone_num");
        String password = httpServletRequest.getParameter("password");
        List<User_required_info> list = iUser_required_infoService.checkIdentity(phoneNumber, password);
        String encodedPhoneNumber = CertificationUtil.encode(phoneNumber);

        if (list.size() == 1) {
            User_required_info user_required_info = list.get(0);
            Cookie cookie1 = new Cookie("phone_number", encodedPhoneNumber);
            cookie1.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            Cookie cookie2 = new Cookie("user_name", CertificationUtil.encode(user_required_info.getUser_name()));
            cookie2.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            Cookie cookie3 = new Cookie("id_card", CertificationUtil.encode(user_required_info.getId_card()));
            cookie3.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            httpServletResponse.addCookie(cookie1);
            httpServletResponse.addCookie(cookie2);
            httpServletResponse.addCookie(cookie3);

            return "redirect:/static/index.html";
        } else {
            return "redirect:/static/login.html";
        }
    }


    @ResponseBody
    @GetMapping(value = "/EugeneLiu")
    public String test() {
        return "EugeneLiu-Identity";
    }
}
