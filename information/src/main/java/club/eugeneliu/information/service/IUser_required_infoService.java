package club.eugeneliu.information.service;

import club.eugeneliu.information.entity.UserInfo;
import club.eugeneliu.information.entity.User_required_info;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface IUser_required_infoService extends IService<User_required_info> {
    boolean insertUserRequiredInfo(User_required_info user_required_info);
    boolean checkIsRegistered(String phoneNumber);
    boolean updateUserName(String user_name,String id_card);
    boolean updateUserPhoneNumber(String phone_number,String id_card);
    boolean updateUserPassword(String password,String id_card);
    UserInfo selectUserIfo(String id_card);

}
