package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Lender_account;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface Lender_accountMapper extends BaseMapper<Lender_account> {
    int insertLender(Lender_account lender_account);
    Double getAccountBalance(@Param("id_card") String id_card);
    int updateAccountBalance(@Param("id_card") String id_card,@Param("new_balance") double new_balance);
}
