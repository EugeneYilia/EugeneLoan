package club.eugeneliu.trade.api;

import club.eugeneliu.trade.entity.Borrower_account;
import club.eugeneliu.trade.entity.Lender_account;
import club.eugeneliu.trade.entity.Recharge_record;
import club.eugeneliu.trade.entity.Withdraw_record;
import club.eugeneliu.trade.service.*;
import club.eugeneliu.trade.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static club.eugeneliu.trade.constants.CertificationConstants.BORROWER;
import static club.eugeneliu.trade.constants.CertificationConstants.LENDER;

@Api("通用控制器")
@RestController
@RequestMapping("/trade")
public class GeneralController {

    @Autowired
    IRecharge_recordService iRecharge_recordService;

    @Autowired
    IBorrower_accountService iBorrower_accountService;

    @Autowired
    ILender_accountService iLender_accountService;

    @Autowired
    IIntend_lendService iIntend_lendService;

    @Autowired
    IWithdraw_recordService iWithdraw_recordService;

    @Autowired
    RestTemplate restTemplate;

    @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "String")
    @ApiOperation(value = "用户充值", notes = "充值指定的金额到平台账户")
    @PostMapping(value = "/general/recharge", produces = "application/json;charset=UTF-8")
    public String rechargeMoney(HttpServletRequest httpServletRequest, @RequestBody Map objects) {
        BigDecimal money = new BigDecimal((String) objects.get("money"));
        String id_card = "";
        String user_type = "";

        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id_card")) {
                try {
                    id_card = CertificationUtil.decode(cookie.getValue());
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

        //充值表中增加记录
        Recharge_record recharge_record = new Recharge_record();
        recharge_record.setRecharge_money(money.doubleValue());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Eugene-Auth", CertificationUtil.encode("eugeneliu"));
        httpHeaders.add("cookie", "id_card=" + CertificationUtil.encode(id_card) + "; user_type=" + CertificationUtil.encode(user_type));

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://192.168.0.163/information/all/bank_account?id_card=" + id_card, HttpMethod.GET, httpEntity, String.class);

        String bank_account = responseEntity.getBody();
        recharge_record.setBank_account(bank_account);
        recharge_record.setRecharge_date(new Date());
        boolean isSuccessful1 = iRecharge_recordService.insertRecord(recharge_record);
        boolean isSuccessful2 = false;
        if (user_type.equals(BORROWER.getIdentity())) {
            //更新借入方资金账户表
            Double account_balance = iBorrower_accountService.getAccountBalance(id_card);
            BigDecimal money2 = new BigDecimal(String.valueOf(account_balance));
            money = money.add(money2);
            isSuccessful2 = iBorrower_accountService.updateAccountBalance(id_card, money.doubleValue());
        } else if (user_type.equals(LENDER.getIdentity())) {
            //更新借出方资金账户表
            Double account_balance = iLender_accountService.getAccountBalance(id_card);
            BigDecimal money2 = new BigDecimal(String.valueOf(account_balance));
            money = money.add(money2);
            isSuccessful2 = iLender_accountService.updateAccountBalance(id_card, money.doubleValue());
        }
        JSONObject result = new JSONObject();
        if (isSuccessful1 && isSuccessful2) {
            result.put("state", "successful");//-1登录失败
            return result.toJSONString();
        } else {
            result.put("state", "error");
            return result.toJSONString();
        }

    }

    @ApiImplicitParam(name = "money", value = "金额", required = true, dataType = "String")
    @ApiOperation(value = "用户提现", notes = "提现指定的金额到银行账户中")
    @PostMapping(value = "/general/withdraw", produces = "application/json;charset=UTF-8")
    public String withdrawMoney(HttpServletRequest httpServletRequest, @RequestBody Map objects) {
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

        //充值表中增加记录
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Eugene-Auth", CertificationUtil.encode("eugeneliu"));
        httpHeaders.add("cookie", "id_card=" + CertificationUtil.encode(id_card) + "; user_type=" + CertificationUtil.encode(user_type));

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://192.168.0.163/information/all/bank_account?id_card=" + id_card, HttpMethod.GET, httpEntity, String.class);

        String bank_account = responseEntity.getBody();

        BigDecimal withdrawMoney = new BigDecimal((String) objects.get("money"));
        BigDecimal bigAccountBalance = null;
        BigDecimal bigForzenMoney = null;
        BigDecimal availableMoney = null;
        if (user_type.equals(BORROWER.getIdentity())) {
            Borrower_account borrower_account = iBorrower_accountService.getAllInformation(id_card);
            bigAccountBalance = new BigDecimal(String.valueOf(borrower_account.getAccount_balance()));
//            bigForzenMoney = new BigDecimal(String.valueOf(iIntend_lendService.getForzenMoney(id_card)));
            availableMoney = bigAccountBalance;
        } else if (user_type.equals(LENDER.getIdentity())) {
            Lender_account lender_account = iLender_accountService.getAllInformation(id_card);
            bigAccountBalance = new BigDecimal(String.valueOf(lender_account.getAccount_balance()));
            Double frozenMoney = iIntend_lendService.getForzenMoney(id_card);
            if(frozenMoney == null){
                bigForzenMoney = new BigDecimal("0");
            } else {
                bigForzenMoney = new BigDecimal(String.valueOf(frozenMoney));
            }
            availableMoney = bigAccountBalance.subtract(bigForzenMoney);
        }


        if (availableMoney.subtract(withdrawMoney).doubleValue() >= 0) {

            Withdraw_record withdraw_record = new Withdraw_record();
            withdraw_record.setBank_account(bank_account);
            withdraw_record.setWithdraw_date(new Date());
            withdraw_record.setWithdraw_money(withdrawMoney.doubleValue());

            //给提现表增加记录
            boolean isSuccessful1 = iWithdraw_recordService.insertRecord(withdraw_record);

            //更新资金账户表
            boolean isSuccessful2 = false;
            if (user_type.equals(BORROWER.getIdentity())) {
                //更新借入方资金账户表
                isSuccessful2 = iBorrower_accountService.updateAccountBalance(id_card, bigAccountBalance.subtract(withdrawMoney).doubleValue());
            } else if (user_type.equals(LENDER.getIdentity())) {
                //更新借出方资金账户表
                isSuccessful2 = iLender_accountService.updateAccountBalance(id_card, bigAccountBalance.subtract(withdrawMoney).doubleValue());
            }

            if (isSuccessful1 && isSuccessful2) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("state", "successful");
                return jsonObject.toJSONString();
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("state", "error");
                return jsonObject.toJSONString();
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("state", "error");
            return jsonObject.toJSONString();
        }

//        return "{'state':'successful'}";
    }


    @GetMapping(value = "/EugeneLiu")
    public String eugeneLiu() {
        return "EugeneLiu-Trade";
    }
}
