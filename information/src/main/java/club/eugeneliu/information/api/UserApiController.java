package club.eugeneliu.information.api;

import club.eugeneliu.information.entity.User_required_info;
import club.eugeneliu.information.mq.RegisterMq;
import club.eugeneliu.information.service.IUser_optional_infoService;
import club.eugeneliu.information.service.IUser_required_infoService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("用户控制器")
@RestController
@RequestMapping("/information")
public class UserApiController {

    @Autowired
    IUser_required_infoService iUser_required_infoService;

    @Autowired
    IUser_optional_infoService iUser_optional_infoService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone_number", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_type", value = "用户类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_name", value = "用户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id_card", value = "身份证号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bank_account", value = "银行卡号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "verify_code", value = "验证码", required = true, dataType = "String")
    })
    @ApiOperation(value = "注册用户信息", notes = "根据用户提交的注册表单，核实验证码，如果没错误，开通第三方存管账户成功，并且跳转到主页，否则返回json提示错误")
    @PostMapping(value = "/all/register",produces = "application/json;charset=UTF-8")
    public String checkIdCard(@RequestBody Map objects) {
//        String phoneNum = httpServletRequest.getParameter("phone_number");
//        String password = httpServletRequest.getParameter("password");
//        String user_type = httpServletRequest.getParameter("user_type");
//        String user_name = httpServletRequest.getParameter("user_name");
//        String id_card = httpServletRequest.getParameter("id_card");
//        String bank_account = httpServletRequest.getParameter("bank_account");
//        String verify_code = httpServletRequest.getParameter("verify_code");

        String phoneNum = (String) objects.get("phone_number");
        String password = (String) objects.get("password");
        String user_type = (String) objects.get("user_type");
        String user_name = (String) objects.get("user_name");
        String id_card = (String) objects.get("id_card");
        String bank_account = (String) objects.get("bank_account");
        String verify_code = (String) objects.get("verify_code");

        System.out.println(phoneNum);
        System.out.println(password);
        System.out.println(user_type);
        System.out.println(user_name);
        System.out.println(id_card);
        System.out.println(bank_account);
        System.out.println(verify_code);

        //验证预留手机号的验证码
        if (!verify_code.equals("123456")) {
            System.out.println("验证码验证失败");
            JSONObject result = new JSONObject();
            result.put("state","error");
            return result.toJSONString();
        } else {
            //进行注册
            User_required_info user_required_info = new User_required_info();
            user_required_info.setBank_account(bank_account);
            user_required_info.setId_card(id_card);
            user_required_info.setPassword(password);
            user_required_info.setPhone_number(phoneNum);
            user_required_info.setUser_name(user_name);
            user_required_info.setUser_type(Integer.parseInt(user_type));

//            boolean isInsertRequiredSuccessful = true;
//            boolean isInsertOptionalSuccessful = true;

            boolean isInsertRequiredSuccessful = iUser_required_infoService.insertUserRequiredInfo(user_required_info);
            boolean isInsertOptionalSuccessful = iUser_optional_infoService.insertUserOptionalInfo(id_card);



            if (isInsertRequiredSuccessful && isInsertOptionalSuccessful) {
                RegisterMq.send(user_type,id_card);
                JSONObject result = new JSONObject();
                result.put("state","successful");
                return result.toJSONString();
            } else {
                System.out.println("用户注册失败");
                JSONObject result = new JSONObject();
                result.put("state","error");
                return result.toJSONString();
            }
        }
    }
}
