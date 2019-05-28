package club.eugeneliu.information.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("用户控制器")
@RestController
@RequestMapping("/information")
public class UserApiController {

    @ApiImplicitParam(name="bank_number",value = "银行卡号",required = true,dataType = "String")
    @ApiOperation(value = "开通第三方存管账户",notes = "输入银行卡号，然后开通第三方存管账号")
    @PostMapping(value = "/all/deposit")
    public String openDeposit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String bank_number = httpServletRequest.getParameter("bank_number");

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="phone_number",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(name="password",value = "密码",required = true,dataType = "String"),
            @ApiImplicitParam(name="user_type",value = "用户类型",required = true,dataType = "String"),
            @ApiImplicitParam(name="user_name",value = "用户姓名",required = true,dataType = "String"),
            @ApiImplicitParam(name="id_card",value = "身份证号",required = true,dataType = "String"),
            @ApiImplicitParam(name="bank_account",value = "银行卡号",required = true,dataType = "String"),
            @ApiImplicitParam(name="verify_code",value = "验证码",required = true,dataType = "String")
    })
    @ApiOperation(value = "注册用户信息",notes = "根据用户提交的注册表单，核实验证码，如果没错误，开通第三方存管账户成功，并且跳转到主页，否则返回json提示错误")
    @PostMapping(value = "/all/register")
    public String checkIdCard(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String phoneNum = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password");
        String user_type = httpServletRequest.getParameter("user_type");
        String user_name = httpServletRequest.getParameter("user_name");
        String id_card = httpServletRequest.getParameter("id_card");
        String bank_account = httpServletRequest.getParameter("bank_account");
        String verify_code = httpServletRequest.getParameter("verify_code");

        //验证预留手机号的验证码
        if(verify_code.equals("123456")){

        }


        //进行注册

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }
}
