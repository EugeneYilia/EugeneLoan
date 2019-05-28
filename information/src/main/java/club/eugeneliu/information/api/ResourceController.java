package club.eugeneliu.information.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/information")
public class ResourceController {
    @ApiOperation(value = "获取用户头像",notes = "根据url返回借入者的头像")
    @GetMapping(value = "/all/avatar")
    public void getAvatar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

    }

    @RequestMapping(value = "/EugeneLiu",method = RequestMethod.GET)
    @ResponseBody
    public String eugeneLiu(){
        return "EugeneLiu-Information";
    }
}
