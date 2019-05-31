package club.eugeneliu.information.service.impl;

import club.eugeneliu.information.entity.User_required_info;
import club.eugeneliu.information.mapper.User_required_infoMapper;
import club.eugeneliu.information.service.IUser_required_infoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Service
public class User_required_infoServiceImpl extends ServiceImpl<User_required_infoMapper, User_required_info> implements IUser_required_infoService {

    @Autowired
    private User_required_infoMapper user_required_infoMapper;

    @Override
    public boolean insertUserRequiredInfo(User_required_info user_required_info) {
        int result = user_required_infoMapper.insertUserRequiredInfo(user_required_info);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIsRegistered(String phoneNumber) {
        int result = user_required_infoMapper.checkIsRegistered(phoneNumber);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserName(String user_name) {
        int result = user_required_infoMapper.updateUserName(user_name);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserPhoneNumber(String phone_number) {
        int result = user_required_infoMapper.updateUserPhoneNumber(phone_number);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserPassword(String password) {
        int result = user_required_infoMapper.updateUserPassword(password);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
