package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Depository;
import club.eugeneliu.trade.mapper.DepositoryMapper;
import club.eugeneliu.trade.service.IDepositoryService;
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
@Service("depositoryService")
public class DepositoryServiceImpl extends ServiceImpl<DepositoryMapper, Depository> implements IDepositoryService {

    @Autowired
    private DepositoryMapper depositoryMapper;

    @Override
    public boolean insertDepository(Depository depository) {
        int result = depositoryMapper.insertDepository(depository);
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }
}
