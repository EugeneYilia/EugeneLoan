package club.eugeneliu.trade.api;

import club.eugeneliu.trade.service.IBorrower_accountService;
import club.eugeneliu.trade.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Api("借入者交易控制器")
@RestController
@RequestMapping("/trade")
public class BorrowerTradeAPIController {


    @Autowired
    IBorrower_accountService iBorrower_accountService;

    @GetMapping(value = "/borrower/limit")
    @ApiOperation(value = "获取用户的额度", notes = "发送get请求，查看自己的可用额度")
    public String getLimit(HttpServletRequest httpServletRequest) {
        String id_card = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id_card")) {
                id_card = cookie.getValue();
                break;
            }
        }
        System.out.println(id_card);
        try {
            id_card = CertificationUtil.decode(id_card);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(id_card);

        JSONObject result = new JSONObject();
        result.put("limit", iBorrower_accountService.selectLimit(id_card));//-1登录失败
        return result.toJSONString();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "intend_money", value = "金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rate", value = "利率", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pay_type", value = "还款方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit_months", value = "还款时间", required = true, dataType = "String")
    })
    @ApiOperation(value = "发起借入事件", notes = "发起贷款，先进入意向借入")
    @PostMapping(value = "/borrower/loan")
    public String launchLoan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String intend_Money = httpServletRequest.getParameter("intend_money");
        String rate = httpServletRequest.getParameter("rate");
        String pay_type = httpServletRequest.getParameter("pay_type");
        String limit_months = httpServletRequest.getParameter("limit_months");

        //Test START
        System.out.println(intend_Money);
        System.out.println(rate);
        System.out.println(pay_type);
        System.out.println(limit_months);
        //Test END

        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());//发起日期
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }


    @ApiOperation(value = "查看自己的待还款记录", notes = "只包含接入用户的未还清钱款记录")
    @GetMapping(value = "/borrower/loan")
    public String getLoan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +
                "'start_date':'successful'," +
                "'start_money':'avatar_url'," +
                "'unpay_money':'successful'," +
                "'next_time_should_pay':'successful'" +
                "'liquidated_money':'successful'" +
                "'pay_rate':'successful'" +
                "'pay_type':'successful'" +
                "'deadline':'successful'" +
                "'start_interest':'successful'" +//计息起始日
                "}";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "bill_id", value = "待还款记录ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "exact_date", value = "日期", required = true, dataType = "String")
    })
    @ApiOperation(value = "还款接口", notes = "对借的钱进行还款")
    @PutMapping(value = "/borrow/loan")
    public String returnMoney(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String bill_id = httpServletRequest.getParameter("bill_id");
        String money = httpServletRequest.getParameter("money");
        String exact_date = httpServletRequest.getParameter("exact_date");

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'state':'successful'}";
    }

    @ApiOperation(value = "借入方已完成记录接口", notes = "借入方查看已经完成的记录")
    @GetMapping(value = "/borrower/finishedLoan")
    public String getFinishedLoans(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'bill_id':'successful'," +
                "'start_date':'successful'," +
                "'money':'avatar_url'," +
                "'interest':'successful'," +
                "'rate':'successful'" +
                "'pay_type':'successful'" +
                "'limit_months':'successful'" +
                "'settle_date':'successful'" +
                "}";
    }
}
