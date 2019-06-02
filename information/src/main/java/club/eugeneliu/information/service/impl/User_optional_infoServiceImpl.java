package club.eugeneliu.information.service.impl;

import club.eugeneliu.information.entity.User_optional_info;
import club.eugeneliu.information.mapper.User_optional_infoMapper;
import club.eugeneliu.information.service.IUser_optional_infoService;
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
public class User_optional_infoServiceImpl extends ServiceImpl<User_optional_infoMapper, User_optional_info> implements IUser_optional_infoService {

    @Autowired
    private User_optional_infoMapper user_optional_infoMapper;

    @Override
    public boolean insertUserOptionalInfo(String id_card) {
        int result = user_optional_infoMapper.insertUserOptionalInfo(id_card);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserOptionalInfo(User_optional_info user_optional_info) {
        int result = user_optional_infoMapper.updateUserOptionalInfo(user_optional_info);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAvatar(Object avatar, String id_card) {
        int result = user_optional_infoMapper.updateAvatar(avatar, id_card);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateSpecialIdentity(String special_identity, String id_card) {
        int result = user_optional_infoMapper.updateSpecialIdentity(special_identity, id_card);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
