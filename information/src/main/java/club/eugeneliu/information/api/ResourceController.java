package club.eugeneliu.information.api;

import club.eugeneliu.information.service.IUser_optional_infoService;
import club.eugeneliu.information.utils.CertificationUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping(value = "/information")
public class ResourceController {

    @Autowired
    IUser_optional_infoService iUser_optional_infoService;

    @ApiOperation(value = "获取用户头像", notes = "根据url返回借入者的头像")
    @GetMapping(value = "/all/avatar")
    public void getAvatar(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

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
        Object temp = iUser_optional_infoService.getAvatar(id_card);
        if (temp == null) {//如果为null,用户未上传过头像，则返回默认头像
            String defaultAvatarPath = "/home/eugeneliu/EEugeneSoft/EugeneLoan/default/EugeneLiu.jpg";
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(new File(defaultAvatarPath));
                int i = fileInputStream.available();
                byte[] buf = new byte[i];
                fileInputStream.read(buf);
                fileInputStream.close();

                httpServletResponse.setContentType("image/jpeg");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                servletOutputStream.write(buf);
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {//返回用户自己的头像
            byte[] byteArray = (byte[]) temp;
            try {
                String data = new String(byteArray, "UTF-8");
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] bytes = decoder.decode(data);
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] < 0) {
                        bytes[i] += 256;
                    }
                }
                httpServletResponse.setContentType("image/jpeg");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                servletOutputStream.write(bytes);
                servletOutputStream.flush();
                servletOutputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "修改用户头像", notes = "修改特定用户的头像")
    @PutMapping(value = "/all/avatar")
    public void updateAvatar(@RequestBody Map objects) {

    }

    @RequestMapping(value = "/EugeneLiu", method = RequestMethod.GET)
    @ResponseBody
    public String eugeneLiu() {
        return "EugeneLiu-Information";
    }
}
