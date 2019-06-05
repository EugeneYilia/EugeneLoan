package club.eugeneliu.trade.api;


import club.eugeneliu.trade.entity.Borrower_account;
import club.eugeneliu.trade.service.IBorrower_accountService;
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
import java.io.IOException;

@Api("借入者账户控制器")
@RestController
@RequestMapping("/trade")
public class BorrowerAccountAPIController {

    @Autowired
    private IBorrower_accountService iBorrower_accountService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "查看借入方资金账户", notes = "返回借入方资金账户的详细信息")
    @GetMapping(value = "/borrower/account", produces = "application/json;charset=UTF-8")
    public String getUserAccount(HttpServletRequest httpServletRequest) {
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

        Borrower_account borrower_account = iBorrower_accountService.getAllInformation(id_card);
        JSONObject result = new JSONObject();
        result.put("user_name", user_name);
        result.put("bank_account", bank_account);
        result.put("account_balance", borrower_account.getAccount_balance());
        result.put("credit_score", borrower_account.getCredit_score());
        result.put("total_limit", borrower_account.getTotal_limit());
        result.put("available_limit", borrower_account.getAvailable_limit());
        return result.toJSONString();
    }
}
