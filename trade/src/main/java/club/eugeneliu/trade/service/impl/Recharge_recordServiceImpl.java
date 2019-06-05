package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Recharge_record;
import club.eugeneliu.trade.mapper.Recharge_recordMapper;
import club.eugeneliu.trade.service.IRecharge_recordService;
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
public class Recharge_recordServiceImpl extends ServiceImpl<Recharge_recordMapper, Recharge_record> implements IRecharge_recordService {

    @Autowired
    Recharge_recordMapper recharge_recordMapper;

    @Override
    public boolean insertRecord(Recharge_record recharge_record) {
        int result = recharge_recordMapper.insertRecord(recharge_record);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }
}
