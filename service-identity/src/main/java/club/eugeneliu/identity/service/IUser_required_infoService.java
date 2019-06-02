package club.eugeneliu.identity.service;

import club.eugeneliu.identity.entity.User_required_info;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */

public interface IUser_required_infoService extends IService<User_required_info> {
    User_required_info checkIdentity(String phoneNumber,String password);
}
