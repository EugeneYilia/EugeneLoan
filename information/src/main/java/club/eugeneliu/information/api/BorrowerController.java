package club.eugeneliu.information.api;

import club.eugeneliu.information.entity.UserInfo;
import club.eugeneliu.information.entity.User_optional_info;
import club.eugeneliu.information.service.IUser_optional_infoService;
import club.eugeneliu.information.service.IUser_required_infoService;
import club.eugeneliu.information.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Api("借入者信息控制器")
@RestController
@RequestMapping("/information")
public class BorrowerController {

    @Autowired
    IUser_required_infoService iUser_required_infoService;

    @Autowired
    IUser_optional_infoService iUser_optional_infoService;

    @ApiOperation(value = "获取借入方个人信息", notes = "请求该url，返回借入者用户信息的json")
    @GetMapping(value = "/borrower/information", produces = "application/json;charset=UTF-8")
    public String getInformation(HttpServletRequest httpServletRequest) {
        String id_card = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id_card")) {
                try {
                    id_card = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        UserInfo userInfo = iUser_required_infoService.selectUserIfo(id_card);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone_number", userInfo.getPhone_number() == null ? "无" : userInfo.getPhone_number());
        jsonObject.put("user_name", userInfo.getUser_name() == null ? "无" : userInfo.getUser_name());
        jsonObject.put("sex", userInfo.getSex() == null ? "无" : userInfo.getSex());
        jsonObject.put("educational_level", userInfo.getEducational_level() == null ? "无" : userInfo.getEducational_level());
        jsonObject.put("marriage", userInfo.getMarriage() == null ? "无" : userInfo.getMarriage());
        jsonObject.put("profession", userInfo.getProfession() == null ? "无" : userInfo.getProfession());
        jsonObject.put("special_identity", userInfo.getSpecial_identity() == null ? "未认证" : "已认证");
        jsonObject.put("address", userInfo.getAddress() == null ? "无" : userInfo.getAddress());

        return jsonObject.toJSONString();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone_number", value = "电话号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别", required = true, dataType = "String"),
            @ApiImplicitParam(name = "educational_level", value = "教育水平", required = true, dataType = "String"),
            @ApiImplicitParam(name = "marriage", value = "婚姻状况", required = true, dataType = "String"),
            @ApiImplicitParam(name = "profession", value = "职业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String")
//            @ApiImplicitParam(name="avatar",value = "头像",required = true,dataType = "MultipartFile")
    })
    @ApiOperation(value = "修改借入方个人信息", notes = "通过该URL，修改借入者用户的信息")
    @PutMapping(value = "/borrower/information", produces = "application/json;charset=UTF-8")
    public String modifyInformation(@RequestBody Map objects, HttpServletRequest httpServletRequest) {

        String id_card = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id_card")) {
                try {
                    id_card = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        String phone_number = (String) objects.get("phone_number");
        String sex = (String) objects.get("sex");
        String educational_level = (String) objects.get("educational_level");
        String marriage = (String) objects.get("marriage");
        String profession = (String) objects.get("profession");
        String address = (String) objects.get("address");

//        System.out.println(phone_number);
//        System.out.println(sex);
//        System.out.println(educational_level);
//        System.out.println(marriage);
//        System.out.println(profession);
//        System.out.println(address);
//        System.out.println(id_card);

        User_optional_info user_optional_info = new User_optional_info();
        user_optional_info.setSex(sex);
        user_optional_info.setEducational_level(educational_level);
        user_optional_info.setMarriage(marriage);
        user_optional_info.setProfession(profession);
        user_optional_info.setAddress(address);
        user_optional_info.setId_card(id_card);

        boolean isSuccessful1 = iUser_optional_infoService.updateUserOptionalInfo(user_optional_info);
//        boolean isSuccessful2 = iUser_required_infoService.updateUserPhoneNumber(phone_number, id_card);

//        System.out.println(isSuccessful1);
        //不能更改手机号
        if (isSuccessful1) {
            JSONObject result = new JSONObject();
            result.put("state", "successful");
            return result.toJSONString();
        } else {
//            System.out.println(isSuccessful1);
//            System.out.println(isSuccessful2);
            JSONObject result = new JSONObject();
            result.put("state", "error");//更该消息失败:可能是前后两次的数据值是一样的,没有任何变化
            return result.toJSONString();
        }

//        if (isSuccessful1 && isSuccessful2) {
//            JSONObject result = new JSONObject();
//            result.put("state", "successful");
//            return result.toJSONString();
//        } else {
//            System.out.println(isSuccessful1);
//            System.out.println(isSuccessful2);
//            JSONObject result = new JSONObject();
//            result.put("state", "error");
//            return result.toJSONString();
//        }

//        Base64.Encoder encoder = Base64.getEncoder();
//        try {
//            String avatar_string = new String(encoder.encode(avatar.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        httpServletResponse.setContentType("application/json;charset=UTF-8");
    }


    @ApiImplicitParam(name = "specify_resource", value = "特殊认证材料", required = true, dataType = "MultipartFile")
    @ApiOperation(value = "特殊认证", notes = "提交特殊申请材料，用于特殊身份认证")
    @PostMapping(value = "/borrower/specialIdentity")
    public String specialIdentity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("specify_resource") MultipartFile specifyResource) {
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            String specialIdentity = new String(encoder.encode(specifyResource.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

}
