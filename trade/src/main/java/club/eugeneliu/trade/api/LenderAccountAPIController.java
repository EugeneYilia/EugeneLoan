package club.eugeneliu.trade.api;

import club.eugeneliu.trade.entity.Lender_account;
import club.eugeneliu.trade.service.IIntend_lendService;
import club.eugeneliu.trade.service.ILender_accountService;
import club.eugeneliu.trade.utils.CertificationUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@Api("借出方账号控制器")
@RestController
@RequestMapping("/trade")
public class LenderAccountAPIController {

    @Autowired
    ILender_accountService iLender_accountService;

    @Autowired
    IIntend_lendService iIntend_lendService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "查看借出方资金账户",notes = "返回借出方资金账户的详细信息")
    @GetMapping(value = "/lender/account")
    public String getUserAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

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
            } else if(cookie.getName().equals("phone_number")){
                try {
                    phone_number = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(cookie.getName().equals("user_type")){
                try {
                    user_type = CertificationUtil.decode(cookie.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Eugene-Auth", CertificationUtil.encode("eugeneliu"));
        httpHeaders.add("cookie","phone_number="+CertificationUtil.encode(phone_number)+"; user_name="+CertificationUtil.encode(user_name)+"; id_card="+CertificationUtil.encode(id_card)+"; user_type="+CertificationUtil.encode(user_type));

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://192.168.0.163/information/all/bank_account?id_card=" + id_card, HttpMethod.GET, httpEntity, String.class);

        String bank_account = responseEntity.getBody();
//        System.out.println("银行卡号:" + bank_account);

        double forzenMoney = iIntend_lendService.getForzenMoney(id_card);
        BigDecimal bigForzenMoney = new BigDecimal(String.valueOf(forzenMoney));

        Lender_account lender_account = iLender_accountService.getAllInformation(id_card);
        BigDecimal bigAccountBalance = new BigDecimal(String.valueOf(lender_account.getAccount_balance()));
        JSONObject result = new JSONObject();
        result.put("user_name", user_name);
        result.put("bank_account", bank_account);
        result.put("current_income", lender_account.getCurrent_income());
        result.put("expected_income", lender_account.getExpected_income());
        result.put("lent_money", lender_account.getLent_money());
        result.put("account_balance", lender_account.getAccount_balance());
        result.put("available_money",bigAccountBalance.subtract(bigForzenMoney).doubleValue());

        return result.toJSONString();
    }
}
