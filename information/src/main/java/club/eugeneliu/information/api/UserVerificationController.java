package club.eugeneliu.information.api;


import club.eugeneliu.information.service.IUser_required_infoService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Api("用户验证控制器")
@RestController
@RequestMapping("/information")
public class UserVerificationController {

    @Autowired
    IUser_required_infoService iUser_required_infoService;

    @ApiImplicitParam(name="phone_number",value = "用户手机号",required = true,dataType = "String")
    @ApiOperation(value = "查询当前手机号是否已经注册",notes = "输入手机号后台查询数据库并返回相应的结果")
    @PostMapping(value = "/all/checkPhoneNumber",produces = "application/json;charset=UTF-8")
    public String checkPhoneNumber(@RequestBody Map objects){
//        String phoneNumber = httpServletRequest.getParameter("phone_number");
        String phoneNumber = (String) objects.get("phone_number");
        boolean isRegistered = iUser_required_infoService.checkIsRegistered(phoneNumber);
        if(isRegistered){
            JSONObject result = new JSONObject();
            result.put("state","successful");
            return result.toJSONString();
        } else {
            JSONObject result = new JSONObject();
            result.put("state","error");
            return result.toJSONString();
        }
    }


    @ApiImplicitParam(name="phone_number",value = "用户手机号",required = true,dataType = "String")
    @ApiOperation(value = "验证手机",notes = "发送验证码验证该手机")
    @PostMapping(value = "/all/checkCode",produces = "application/json;charset=UTF-8")
    public String sendCheckCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String phoneNumber = httpServletRequest.getParameter("phone_number");
        // --------->Send CheckCode

        // 默认发送成功
        JSONObject result = new JSONObject();
        result.put("state","successful");
        return result.toJSONString();
    }

    @ApiImplicitParam(name="check_code",value = "手机验证码",required = true,dataType = "String")
    @ApiOperation(value = "核实验证码",notes = "检验用户提交的验证码是否正确")
    @PostMapping(value = "/all/checkCode",produces = "application/json;charset=UTF-8")
    public String checkCheckCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String phoneNumber = httpServletRequest.getParameter("phone_number");


        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="user_name",value = "用户姓名",required = true,dataType = "String"),
            @ApiImplicitParam(name="id_card",value = "身份证号",required = true,dataType = "String"),
            @ApiImplicitParam(name="idcard_font",value = "身份证前面照",required = true,dataType = "MultipartFile"),
            @ApiImplicitParam(name="idcard_back",value = "身份证背面照",required = true,dataType = "MultipartFile"),
    })
    @ApiOperation(value = "验证用户的身份信息",notes = "上传用户姓名、身份证号以及身份证正反面，通过第三方接口核实用户信息，然后返回相应的结果")
    @PostMapping(value = "/all/checkIdCard")
    public String checkIdCard(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("idcard_font")MultipartFile idCardFront, @RequestParam("idcard_back") MultipartFile idCardBack){
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            String idCardFrontString = new String(encoder.encode(idCardFront.getBytes()));
            String idCardBackString = new String(encoder.encode(idCardBack.getBytes()));
            String userNanme = httpServletRequest.getParameter("user_name");
            String id_card = httpServletRequest.getParameter("id_card");
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }
}
