package club.eugeneliu.trade.api;

import club.eugeneliu.trade.service.IBorrower_accountService;
import club.eugeneliu.trade.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Api("借入者交易控制器")
@RestController
@RequestMapping("/trade")
public class BorrowerTradeAPIController {


    @Autowired
    private RestTemplate restTemplate;

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
        result.put("limit", iBorrower_accountService.getLimit(id_card));//-1登录失败
        return result.toJSONString();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "intend_money", value = "金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rate", value = "利率", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pay_type", value = "还款方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit_months", value = "还款时间", required = true, dataType = "String")
    })
    @ApiOperation(value = "发起借入事件", notes = "发起贷款，先进入意向借入")
    @PostMapping(value = "/borrower/loan", produces = "application/json;charset=UTF-8")
    public String launchLoan(HttpServletRequest httpServletRequest, @RequestBody Map objects) {
        Double intend_Money = Double.parseDouble((String) objects.get("intend_money"));
        String rate = (String) objects.get("rate");
        String pay_type = (String) objects.get("pay_type");
        String limit_months = (String) objects.get("limit_months");

        String user_name = "";
        String id_card = "";
        String phone_number = "";
        String user_type = "";


        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_name")) {
                try {
                    user_name = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (cookie.getName().equals("id_card")) {
                try {
                    id_card = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (cookie.getName().equals("phone_number")) {
                try {
                    phone_number = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (cookie.getName().equals("user_type")) {
                try {
                    user_type = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Eugene-Auth", CertificationUtil.encode("eugeneliu"));
        httpHeaders.add("cookie", "phone_number=" + CertificationUtil.encode(phone_number) + "; user_name=" + CertificationUtil.encode(user_name) + "; id_card=" + CertificationUtil.encode(id_card) + "; user_type=" + CertificationUtil.encode(user_type));

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://192.168.0.163/information/all/speicial_identity?id_card=" + id_card, HttpMethod.GET, httpEntity, String.class);

        String special_identity = responseEntity.getBody();

        //先比较账目总数
        if(special_identity.equals("NormalPeople")) {//最多一个账目
            int trade_number = 0;

            int intend_borrow_number = 0;
        } else if(special_identity.equals("EugeneLiu")){//最多三个账目
            int trade_number = 0;

            int intend_borrow_number = 0;
        }
        //再比较额度


        //给intend_borrow表增加字段

        //再修改资金账户表的额度字段


        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());//发起日期
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
