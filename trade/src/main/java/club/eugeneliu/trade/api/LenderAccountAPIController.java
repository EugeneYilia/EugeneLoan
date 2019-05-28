package club.eugeneliu.trade.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("借出方账号控制器")
@RestController
@RequestMapping("/trade")
public class LenderAccountAPIController {
    @ApiOperation(value = "查看借出方资金账户",notes = "返回借出方资金账户的详细信息")
    @GetMapping(value = "/lender/account")
    public String getUserAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return "{'username':'successful'," +
                "'bank_account':'successful'," +
                "'user_balance':'successful'," +
                "'lend_money':'successful'," +
                "'avatar':'avatar_url',"+
                "'total_benefit':'successful',"+
                "'future_benefit':'successful'"+
                "}";
    }
}
