package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Trade;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Component
public interface TradeMapper extends BaseMapper<Trade> {
    int getTradeNumber(@Param("in_bound_account") String in_bound_account);
    List<Trade> getUnfinishedLoans(@Param("in_bound_account") String in_bound_account);

}
