package club.eugeneliu.trade.api;

import club.eugeneliu.trade.entity.Intend_borrow;
import club.eugeneliu.trade.entity.Trade;
import club.eugeneliu.trade.service.IBorrower_accountService;
import club.eugeneliu.trade.service.IIntend_borrowService;
import club.eugeneliu.trade.service.ITradeService;
import club.eugeneliu.trade.utils.CertificationUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api("借入者交易控制器")
@RestController
@RequestMapping("/trade")
public class BorrowerTradeAPIController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    IBorrower_accountService iBorrower_accountService;

    @Autowired
    ITradeService iTradeService;

    @Autowired
    IIntend_borrowService iIntend_borrowService;

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
//        System.out.println(id_card);
        try {
            id_card = CertificationUtil.decode(id_card);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(id_card);

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

//        System.out.println((String) objects.get("intend_money"));
//        System.out.println((String) objects.get("rate"));
//        System.out.println((String) objects.get("pay_type"));
//        System.out.println((String) objects.get("limit_months"));

        Double intend_Money = Double.parseDouble((String) objects.get("intend_money"));
        Float rate = Float.parseFloat((String) objects.get("rate"));//0.01
        int pay_type = Integer.parseInt((String) objects.get("pay_type"));//1->按月付 3->按季付
        int limit_months = Integer.parseInt((String) objects.get("limit_months"));//5 6 8 9

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

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Eugene-Auth", CertificationUtil.encode("eugeneliu"));
//        httpHeaders.add("cookie", "phone_number=" + CertificationUtil.encode(phone_number) + "; user_name=" + CertificationUtil.encode(user_name) + "; id_card=" + CertificationUtil.encode(id_card) + "; user_type=" + CertificationUtil.encode(user_type));
//
//        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
//        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://192.168.0.163/information/all/speicial_identity?id_card=" + id_card, HttpMethod.GET, httpEntity, String.class);
//
//        String special_identity = responseEntity.getBody();

        String funds_account = iBorrower_accountService.getFundsAccount(id_card);
        //先比较账目总数
//        if (special_identity.equals("NormalPeople")) {//最多一个账目
//            int trade_number = iTradeService.getTradeNumber(funds_account);
//            if (trade_number >= 1) {
//                JSONObject result = new JSONObject();
//                result.put("state", "error");
//                return result.toJSONString();
//            }
//            int intend_borrow_number = iIntend_borrowService.getIntendNumber(id_card);
//            if ((intend_borrow_number + trade_number) >= 1) {
//                JSONObject result = new JSONObject();
//                result.put("state", "error");
//                return result.toJSONString();
//            }
//
//        } else if (special_identity.equals("EugeneLiu")) {//最多三个账目
//            int trade_number = iTradeService.getTradeNumber(funds_account);
//            if (trade_number >= 3) {
//                JSONObject result = new JSONObject();
//                result.put("state", "error");
//                return result.toJSONString();
//            }
//            int intend_borrow_number = 0;
//            if ((intend_borrow_number + trade_number) >= 3) {
//                JSONObject result = new JSONObject();
//                result.put("state", "error");
//                return result.toJSONString();
//            }
//        }

//        System.out.println("funds_account:" + funds_account);

        int trade_number = iTradeService.getTradeNumber(funds_account);
        if (trade_number >= 1) {//每人最多只能借一个账目
            JSONObject result = new JSONObject();
            result.put("state", "error");
            return result.toJSONString();
        }

        int intend_borrow_number = iIntend_borrowService.getIntendNumber(id_card);
        if ((intend_borrow_number + trade_number) >= 1) {
            JSONObject result = new JSONObject();
            result.put("state", "error");
            return result.toJSONString();
        }
//        System.out.println("账目OK");

        //再比较额度
        Double available_limit = iBorrower_accountService.getLimit(id_card);
        if (intend_Money > available_limit) {
            JSONObject result = new JSONObject();
            result.put("state", "error");
            return result.toJSONString();
        }
//        System.out.println("额度OK");

        //给intend_borrow表增加字段
        Intend_borrow intend_borrow = new Intend_borrow();
        intend_borrow.setId_card(id_card);
        intend_borrow.setIntend_money(intend_Money);
        intend_borrow.setStart_date(new Date());
        intend_borrow.setPay_rate(rate);
        intend_borrow.setPay_type(pay_type);
        intend_borrow.setLimit_months(limit_months);
        intend_borrow.setState(1);
        intend_borrow.setRaised_money(0.0);
        boolean isSuccessful1 = iIntend_borrowService.insertIntendBorrow(intend_borrow);

        //再修改资金账户表的额度字段
        double new_available_limit = available_limit - intend_Money;
        boolean isSuccessful2 = iBorrower_accountService.updateAvailableLimit(id_card, new_available_limit);

//        System.out.println(isSuccessful1);
//        System.out.println(isSuccessful2);

        if (isSuccessful1 && isSuccessful2) {
            JSONObject result = new JSONObject();
            result.put("state", "successful");
            return result.toJSONString();
        } else {
            JSONObject result = new JSONObject();
            result.put("state", "error");
            return result.toJSONString();
        }

//        String start_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());//发起日期
    }


    @ApiOperation(value = "借入方待交易页面接口", notes = "查看未达成的贷款记录")
    @GetMapping(value = "/borrower/unfinishedLoan",produces = "application/json;charset=UTF-8")
    public String getIntendedLoans(HttpServletRequest httpServletRequest) {
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

        Intend_borrow intend_borrow = iIntend_borrowService.getIntendedLoans(id_card);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bill_id",intend_borrow.getBill_id());
        jsonObject.put("intend_money",intend_borrow.getIntend_money());
        jsonObject.put("start_date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(intend_borrow.getStart_date()));
        jsonObject.put("rate",intend_borrow.getPay_rate());
        jsonObject.put("pay_type",intend_borrow.getPay_type());
        jsonObject.put("limit_months",intend_borrow.getLimit_months());
        jsonObject.put("raised_money",intend_borrow.getRaised_money());
        jsonArray.add(jsonObject);
        System.out.println("OK");
        return jsonArray.toString();
    }


//    @ApiOperation(value = "查看自己的待还款记录", notes = "只包含接入用户的未还清钱款记录")
//    @GetMapping(value = "/borrower/loan", produces = "application/json;charset=UTF-8")
//    public String getLoan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        String id_card = "";
//        Cookie[] cookies = httpServletRequest.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("id_card")) {
//                try {
//                    id_card = CertificationUtil.decode(cookie.getValue());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        String in_bound_account = iBorrower_accountService.getFundsAccount(id_card);
//
////        JSONArray jsonArray = new JSONArray();
//        Trade unfinishedTrade = iTradeService.getUnfinishedLoans(in_bound_account);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("bill_id", unfinishedTrade.getBill_id());
//        jsonObject.put("start_date", unfinishedTrade.getExact_date());
//        jsonObject.put("start_money", unfinishedTrade.getMoney());
//        jsonObject.put("unpay_money", unfinishedTrade.getMoney());
////        jsonObject.put("next_time_should_pay",);
////        jsonObject.put("liquidated_money",);
////        jsonObject.put("pay_rate",unfinishedTrade.getPay_rate());
////        jsonObject.put("pay_type",unfinishedTrade.getPay_type());
////        jsonObject.put("deadline",);
////        jsonObject.put("start_interest",);
//
//        return jsonObject.toJSONString();
//    }

    @ApiOperation(value = "借入方已完成记录接口", notes = "借入方查看已经完成的记录")
    @GetMapping(value = "/borrower/finishedLoan")
    public String getFinishedLoans(HttpServletRequest httpServletRequest) {

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

        String in_bound_account = iBorrower_accountService.getFundsAccount(id_card);//in_bound_account就是fund_account

        JSONArray jsonArray = new JSONArray();
        List<Trade> trades = iTradeService.getFinishedLoans(in_bound_account);
        for(Trade trade:trades){
            int limit_months = trade.getLimit_months();
            BigDecimal bigInterest = new BigDecimal(String.valueOf());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bill_id",trade.getBill_id());
            jsonObject.put("start_date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(trade.getExact_date()));
            jsonObject.put("money",trade.getMoney());
            jsonObject.put("interest",trade.getPay_rate());
            jsonObject.put("rate",trade.getPay_rate());//月利率

            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }



//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "bill_id", value = "待还款记录ID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "exact_date", value = "日期", required = true, dataType = "String")
//    })
//    @ApiOperation(value = "还款接口", notes = "对借的钱进行还款")
//    @PutMapping(value = "/borrower/loan")
//    public String returnMoney(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        String bill_id = httpServletRequest.getParameter("bill_id");
//        String money = httpServletRequest.getParameter("money");
//        String exact_date = httpServletRequest.getParameter("exact_date");
//
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        return "{'state':'successful'}";
//    }
}
