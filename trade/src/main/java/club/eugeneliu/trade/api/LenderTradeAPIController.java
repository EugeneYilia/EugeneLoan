package club.eugeneliu.trade.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("借出者交易控制器")
@RestController
@RequestMapping("/trade")
public class LenderTradeAPIController {
    @ApiImplicitParams({
            @ApiImplicitParam(name="expect_rate",value = "预期利率",required = true,dataType = "String"),
            @ApiImplicitParam(name="lend_money",value = "金额",required = true,dataType = "String"),
            @ApiImplicitParam(name="exact_date",value = "日期",required = true,dataType = "String"),
    })
    @ApiOperation(value = "寻找可借入用户",notes = "将自己的条件发过来收到后，返回最匹配的列表")
    @PostMapping(value = "/lender/searchBorrowers")
    public String modifyInformation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("avatar") MultipartFile avatar){
        String exact_rate = httpServletRequest.getParameter("exact_rate");
        String lend_money = httpServletRequest.getParameter("lend_money");
        String exact_date = httpServletRequest.getParameter("exact_date");

        //返回匹配的用户列表  前三个
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

    @ApiImplicitParams({
            @ApiImplicitParam(name="bill_id",value = "账单id",required = true,dataType = "String"),
            @ApiImplicitParam(name="lend_money",value = "金额",required = true,dataType = "String"),
            @ApiImplicitParam(name="intend_lend_date",value = "意向借出日期",required = true,dataType = "String"),
    })
    @ApiOperation(value = "我要借出",notes = "将对特定的用户发起意向借出")
    @PostMapping(value = "/lender/loan")
    public String launchLoan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String bill_id = httpServletRequest.getParameter("bill_id");
        String lend_money = httpServletRequest.getParameter("lend_money");
        String intend_lend_date = httpServletRequest.getParameter("intend_lend_date");

        //返回匹配的用户列表
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful/fail'}";
    }

    @ApiOperation(value="查看自己的待收款记录",notes = "只包含借出用户的待收钱款记录")
    @GetMapping(value = "/lender/loan")
    public String getLoan(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +
                "'start_date':'successful'," +
                "'money':'avatar_url',"+
                "'uncollected_money':'successful',"+
                "'pay_rate':'successful'"+
                "'pay_type':'successful'"+
                "'last_pay_date':'successful'"+//计息起始日
                "}";
    }

    @ApiOperation(value = "借出方已完成记录接口",notes = "借出方已经完成的贷款记录")
    @GetMapping(value = "/lender/finishedLoan")
    public String getFinishedLoans(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +//账单ID
                "'start_date':'successful'," +//起始日期
                "'money':'avatar_url',"+//初始本金
                "'real_interest':'successful',"+//实际利息
                "'rate':'successful'"+//利率
                "'limit_months':'successful'"+//期限
                "'pay_up_date':'successful'"+//结清日期
                "}";
    }

    @ApiOperation(value = "借出方待完成交易接口",notes = "返回借出方关于待完成交易相关的信息")
    @GetMapping(value = "/lender/unfinishedLoan")
    public String getUnfinishedLoan(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +//账单ID
                "'intend_money':'successful'," +//意向借出款项
                "'start_date':'avatar_url',"+//借出日期
                "'rate':'successful',"+//利率
                "'pay_type':'successful'"+//支付类型
                "'limit_months':'successful'"+//期限
                "'state':'successful'"+//状态
                "'raised_money':'successful'"+
                "'lend_money':'successful'"+
                "'exact_date':'successful'"+
                "}";
    }
}
