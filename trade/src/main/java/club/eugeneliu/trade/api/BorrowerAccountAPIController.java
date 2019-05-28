package club.eugeneliu.trade.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("借入者账户控制器")
@RestController
@RequestMapping("/trade")
public class BorrowerAccountAPIController {
    @ApiOperation(value = "查看借入方资金账户",notes = "返回借入方资金账户的详细信息")
    @GetMapping(value = "/borrower/account")
    public String getUserAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'user_name':'successful'," +
                "'bank_account':'successful'," +
                "'user_balance':'avatar_url',"+
                "'score':'asfsa',"+
                "'avatar':'avatar_url',"+
                "'total_quato':'dsadsada',"+
                "'unused_quato':'successful'"+
                "}";
    }
}
