package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Lender_account;
import club.eugeneliu.trade.mapper.Lender_accountMapper;
import club.eugeneliu.trade.service.ILender_accountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.netflix.discovery.converters.Auto;
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
public class Lender_accountServiceImpl extends ServiceImpl<Lender_accountMapper, Lender_account> implements ILender_accountService {

    @Autowired
    Lender_accountMapper lender_accountMapper;

    @Override
    public boolean insertLender(Lender_account lender_account) {
        int result = lender_accountMapper.insertLender(lender_account);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
