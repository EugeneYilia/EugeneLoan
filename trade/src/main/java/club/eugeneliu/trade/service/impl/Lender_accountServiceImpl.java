package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Lender_account;
import club.eugeneliu.trade.mapper.Lender_accountMapper;
import club.eugeneliu.trade.service.ILender_accountService;
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
@Service("lender_accountService")
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

    @Override
    public Double getAccountBalance(String id_card) {
        return lender_accountMapper.getAccountBalance(id_card);
    }

    @Override
    public boolean updateAccountBalance(String id_card, double new_balance) {
        int result = lender_accountMapper.updateAccountBalance(id_card, new_balance);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Lender_account getAllInformation(String id_card) {
        return lender_accountMapper.getAllInformation(id_card);
    }
}
