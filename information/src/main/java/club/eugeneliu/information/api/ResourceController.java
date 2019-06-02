package club.eugeneliu.information.api;

import club.eugeneliu.information.service.IUser_optional_infoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/information")
public class ResourceController {

    IUser_optional_infoService iUser_optional_infoService;

    @ApiOperation(value = "获取用户头像",notes = "根据url返回借入者的头像")
    @GetMapping(value = "/all/avatar")
    public void getAvatar(@RequestBody Map objects){
//        byte[] byteArray = (byte[])
    }

    @ApiOperation(value = "修改用户头像",notes = "修改特定用户的头像")
    @PutMapping(value = "/all/avatar")
    public void updateAvatar(@RequestBody Map objects){

    }

    @RequestMapping(value = "/EugeneLiu",method = RequestMethod.GET)
    @ResponseBody
    public String eugeneLiu(){
        return "EugeneLiu-Information";
    }
}
