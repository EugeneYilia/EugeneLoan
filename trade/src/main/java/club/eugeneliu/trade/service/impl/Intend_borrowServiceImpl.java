package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Intend_borrow;
import club.eugeneliu.trade.mapper.Intend_borrowMapper;
import club.eugeneliu.trade.service.IIntend_borrowService;
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
@Service
public class Intend_borrowServiceImpl extends ServiceImpl<Intend_borrowMapper, Intend_borrow> implements IIntend_borrowService {

    @Autowired
    private Intend_borrowMapper intend_borrowMapper;

    @Override
    public int getIntendNumber(String id_card) {
        return intend_borrowMapper.getIntendNumber(id_card);
    }

    @Override
    public boolean insertIntendBorrow(Intend_borrow intend_borrow) {
        int result = intend_borrowMapper.insertIntendBorrow(intend_borrow);
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Intend_borrow getIntendedLoans(String id_card) {
        return intend_borrowMapper.getIntendedLoans(id_card);
    }
}
