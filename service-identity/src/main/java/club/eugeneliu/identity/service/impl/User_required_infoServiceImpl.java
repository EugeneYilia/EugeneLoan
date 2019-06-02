package club.eugeneliu.identity.service.impl;

import club.eugeneliu.identity.entity.User_required_info;
import club.eugeneliu.identity.mapper.User_required_infoMapper;
import club.eugeneliu.identity.service.IUser_required_infoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Service("UserRequiredInfoServiceImpl1")
public class User_required_infoServiceImpl extends ServiceImpl<User_required_infoMapper, User_required_info> implements IUser_required_infoService {

    @Autowired
    private User_required_infoMapper user_required_infoMapper;

    @Override
    public User_required_info checkIdentity(String phoneNumber, String password) {
        return user_required_infoMapper.checkIdentity(phoneNumber,password);
    }
}
