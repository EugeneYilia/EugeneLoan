package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Depository;
import club.eugeneliu.trade.entity.Lender_account;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Component
public interface DepositoryMapper extends BaseMapper<Depository> {
    int insertDepository(Depository depository);
}
