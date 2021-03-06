package club.eugeneliu.information.mapper;

import club.eugeneliu.information.entity.UserInfo;
import club.eugeneliu.information.entity.User_required_info;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Component
public interface User_required_infoMapper extends BaseMapper<User_required_info> {
    int insertUserRequiredInfo(User_required_info user_required_info);
    int checkIsRegistered(@Param("phone_number") String phoneNumber);
    int updateUserName(@Param("user_name") String user_name,@Param("id_card") String id_card);

    int updateUserPhoneNumber(@Param("phone_number") String phone_number,@Param("id_card") String id_card);

    int updateUserPassword(@Param("password") String password,@Param("id_card") String id_card);
    UserInfo selectUserInfo(@Param("id_card") String id_card);
    String getBankAccount(@Param("id_card") String id_card);
}
