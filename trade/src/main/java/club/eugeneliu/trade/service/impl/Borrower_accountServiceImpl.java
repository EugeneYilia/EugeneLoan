package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Borrower_account;
import club.eugeneliu.trade.mapper.Borrower_accountMapper;
import club.eugeneliu.trade.service.IBorrower_accountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Service("borrower_accountService")
public class Borrower_accountServiceImpl extends ServiceImpl<Borrower_accountMapper, Borrower_account> implements IBorrower_accountService {

    @Autowired
    Borrower_accountMapper borrower_accountMapper;

    @Override
    public boolean insertBorrower(Borrower_account borrower_account) {
        int result = borrower_accountMapper.insertBorrower(borrower_account);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Double getLimit(String id_card) {
        return borrower_accountMapper.getLimit(id_card);
    }

    @Override
    public Borrower_account getAllInformation(String id_card) {
        return borrower_accountMapper.getAllInformation(id_card);
    }

    @Override
    public Double getAccountBalance(String id_card) {
        return borrower_accountMapper.getAccountBalance(id_card);
    }

    @Override
    public boolean updateAccountBalance(String id_card, double new_balance) {
        int result = borrower_accountMapper.updateAccountBalance(id_card,new_balance);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }


}
