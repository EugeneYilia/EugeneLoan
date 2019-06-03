package club.eugeneliu.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/resources")
public class EugeneLiuController {
    @GetMapping("/eugeneliu/eugeneliu")
    public String eugeneliu(){
        return "/eugeneliu/eugeneliu";
    }

}
