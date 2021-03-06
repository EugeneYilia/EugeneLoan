package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Intend_lend;
import club.eugeneliu.trade.mapper.Intend_lendMapper;
import club.eugeneliu.trade.service.IIntend_lendService;
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
public class Intend_lendServiceImpl extends ServiceImpl<Intend_lendMapper, Intend_lend> implements IIntend_lendService {

    @Autowired
    private Intend_lendMapper intend_lendMapper;

    @Override
    public Double getForzenMoney(String id_card) {
        return intend_lendMapper.getForzenMoney(id_card);
    }
}
