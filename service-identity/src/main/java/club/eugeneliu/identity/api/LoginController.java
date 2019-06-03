package club.eugeneliu.identity.api;

import club.eugeneliu.identity.entity.User_required_info;
import club.eugeneliu.identity.service.IUser_required_infoService;
import club.eugeneliu.identity.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api("登录控制器")
@Controller
@RequestMapping("/identity")
public class LoginController {

    @Resource(name = "UserRequiredInfoServiceImpl1")
    private IUser_required_infoService iUser_required_infoService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone_number", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @ApiOperation(value = "验证用户信息", notes = "验证用户的身份，如果登陆成功，生成session，之后这个模块作为身份验证服务,成功就跳转到首页否则返回json告诉登录失败")
    @PostMapping(value = "/all/login",produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String userLogin(@RequestBody Map objects,HttpServletResponse httpServletResponse) {
        String phoneNumber = (String) objects.get("phone_number");
        String password = (String) objects.get("password");

        System.out.println(phoneNumber);
        System.out.println(password);
        if(phoneNumber==null||password==null){
            JSONObject result = new JSONObject();
            result.put("state","-1");//-1登录失败
            return result.toJSONString();
        }

        User_required_info user_required_info = iUser_required_infoService.checkIdentity(phoneNumber, password);



        System.out.println(user_required_info==null?null:user_required_info.getId_card());

        if (user_required_info != null) {
            //使用自己的授权方式,最简版本的授权
            //不依赖于Session,减少了服务器端的压力
            Cookie cookie1 = new Cookie("phone_number", CertificationUtil.encode(phoneNumber));
            cookie1.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            cookie1.setDomain("192.168.0.163");
            cookie1.setPath("/");

            Cookie cookie2 = new Cookie("user_name", CertificationUtil.encode(user_required_info.getUser_name()));
            cookie2.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            cookie2.setDomain("192.168.0.163");
            cookie2.setPath("/");

            Cookie cookie3 = new Cookie("id_card", CertificationUtil.encode(user_required_info.getId_card()));
            cookie3.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            cookie3.setDomain("192.168.0.163");
            cookie3.setPath("/");

            Cookie cookie4 = new Cookie("user_type", CertificationUtil.encode(String.valueOf(user_required_info.getUser_type())));
            cookie4.setMaxAge(24 * 60 * 60);//cookie有效期为一天
            cookie4.setDomain("192.168.0.163");
            cookie4.setPath("/");

            httpServletResponse.addCookie(cookie1);
            httpServletResponse.addCookie(cookie2);
            httpServletResponse.addCookie(cookie3);
            httpServletResponse.addCookie(cookie4);
            JSONObject result = new JSONObject();
            result.put("state",user_required_info.getUser_type());//0借入者，1借出者
            return result.toJSONString();
//            if(user_required_info.getUser_type() == 0){
//                JSONObject result = new JSONObject();
//                result.put("state",user_required_info.getUser_type());
//                return result.toJSONString();
////                return "192.168.0.163/static/borrower/index_borrower.html";
//            } else if(user_required_info.getUser_type() == 1){
//                JSONObject result = new JSONObject();
//                result.put("user_type",user_required_info.getUser_type());
//                result.put("state","successful");
//                return result.toJSONString();
////                return "192.168.0.163/static/lender/index_lender.html";
//            }
//            return "redirect:192.168.0.163/static/index.html";
        } else {
            JSONObject result = new JSONObject();
            result.put("state","-1");//-1登录失败
            return result.toJSONString();
            //登录失败，返回到首页
//            return "redirect:192.168.0.163/static/login.html";
        }
    }


    @ResponseBody
    @GetMapping(value = "/EugeneLiu")
    public String test() {
        return "EugeneLiu-Identity";
    }
}
