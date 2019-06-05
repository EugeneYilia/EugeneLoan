package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Trade;
import club.eugeneliu.trade.mapper.TradeMapper;
import club.eugeneliu.trade.service.ITradeService;
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
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public int getTradeNumber(String in_bound_account) {
        return tradeMapper.getTradeNumber(in_bound_account);
    }

    @Override
    public Trade getUnfinishedLoans(String in_bound_account) {
        return tradeMapper.getUnfinishedLoans(in_bound_account);
    }

    @Override
    public List<Trade> getFinishedLoans(String in_bound_account) {
        return tradeMapper.getFinishedLoans(in_bound_account);
    }
}
