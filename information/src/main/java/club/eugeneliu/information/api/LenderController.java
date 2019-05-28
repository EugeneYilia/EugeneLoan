package club.eugeneliu.information.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Api("借出者信息控制器")
@RestController
@RequestMapping("/information")
public class LenderController {
    @ApiOperation(value = "获取借出方个人信息",notes = "请求该url，返回借出者用户信息的json")
    @GetMapping(value = "/lender/information")
    public String getInformation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'phone_number':'successful'," +
                "'user_name':'successful'," +
                "'sex':'successful',"+
                "'educational_level':'successful',"+
                "'marriage':'successful',"+
                "'profession':'successful',"+
                "'address':'successful',"+
                "'avatar':'avatar_url',"+
                "}";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="phone_number",value = "电话号码",required = true,dataType = "String"),
            @ApiImplicitParam(name="sex",value = "性别",required = true,dataType = "String"),
            @ApiImplicitParam(name="educational_level",value = "教育水平",required = true,dataType = "String"),
            @ApiImplicitParam(name="marriage",value = "婚姻状况",required = true,dataType = "String"),
            @ApiImplicitParam(name="profession",value = "职业",required = true,dataType = "String"),
            @ApiImplicitParam(name="address",value = "地址",required = true,dataType = "String"),
            @ApiImplicitParam(name="avatar",value = "头像",required = true,dataType = "MultipartFile")
    })
    @ApiOperation(value = "修改借出方个人信息",notes = "通过该URL，修改借出者用户的信息")
    @PutMapping(value = "/lender/information")
    public String modifyInformation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("avatar") MultipartFile avatar){
        String phone_number = httpServletRequest.getParameter("phone_number");
        String sex = httpServletRequest.getParameter("sex");
        String educational_level = httpServletRequest.getParameter("educational_level");
        String marriage = httpServletRequest.getParameter("marriage");
        String profession = httpServletRequest.getParameter("profession");
        String address = httpServletRequest.getParameter("address");
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            String avatar_string = new String(encoder.encode(avatar.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful/fail'}";
    }
}
