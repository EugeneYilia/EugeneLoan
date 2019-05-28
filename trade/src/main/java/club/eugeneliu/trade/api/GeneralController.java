package club.eugeneliu.trade.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("通用控制器")
@RestController
@RequestMapping("/trade")
public class GeneralController {
    @ApiImplicitParam(name="money",value = "金额",required = true,dataType = "String")
    @ApiOperation(value = "用户充值",notes = "充值指定的金额到平台账户")
    @PostMapping(value = "/general/recharge")
    public String rechargeMoney(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String phoneNumber = httpServletRequest.getParameter("money");

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

    @ApiImplicitParam(name="money",value = "金额",required = true,dataType = "String")
    @ApiOperation(value = "用户提现",notes = "提现指定的金额到银行账户中")
    @PostMapping(value = "/general/withdraw")
    public String withdrawMoney(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String phoneNumber = httpServletRequest.getParameter("money");

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

    @ApiOperation(value = "待交易页面接口",notes = "查看未达成的贷款记录")
    @GetMapping(value = "/general/unfinishedLoan")
    public String getFinishedLoans(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +
                "'intend_money':'successful'," +
                "'start_date':'avatar_url',"+
                "'rate':'successful',"+
                "'pay_type':'successful'"+
                "'limit_months':'successful'"+
                "'state':'successful'"+
                "'raised_money':'successful'"+
                "}";
    }

    @GetMapping(value = "/EugeneLiu")
    public String eugeneLiu(){
        return "EugeneLiu-Trade";
    }
}
