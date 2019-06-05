package club.eugeneliu.trade.service;

import club.eugeneliu.trade.entity.Trade;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface ITradeService extends IService<Trade> {
    int getTradeNumber(String in_bound_account);
    Trade getUnfinishedLoans(String in_bound_account);
    List<Trade> getFinishedLoans(String in_bound_account);
}
