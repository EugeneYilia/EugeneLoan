package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Withdraw_record;
import club.eugeneliu.trade.mapper.Withdraw_recordMapper;
import club.eugeneliu.trade.service.IWithdraw_recordService;
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
public class Withdraw_recordServiceImpl extends ServiceImpl<Withdraw_recordMapper, Withdraw_record> implements IWithdraw_recordService {

    @Autowired
    Withdraw_recordMapper withdraw_recordMapper;

    @Override
    public boolean insertRecord(Withdraw_record withdraw_record) {
        int result = withdraw_recordMapper.insertRecord(withdraw_record);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }
}
