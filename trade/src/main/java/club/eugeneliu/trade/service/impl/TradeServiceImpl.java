package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Trade;
import club.eugeneliu.trade.mapper.TradeMapper;
import club.eugeneliu.trade.service.ITradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {

}
