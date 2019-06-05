package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Borrower_account;
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
public interface Borrower_accountMapper extends BaseMapper<Borrower_account> {
    int insertBorrower(Borrower_account borrower_account);
    Double getLimit(@Param("id_card") String id_card);
    Borrower_account getAllInformation(@Param("id_card") String id_card);
    Double getAccountBalance(@Param("id_card") String id_card);
    int updateAccountBalance(@Param("id_card") String id_card,@Param("new_balance") double new_balance);
    String getFundsAccount(@Param("id_card") String id_card);
    int updateAvailableLimit(@Param("id_card") String id_card,@Param("available_limit") double available_limit);
}
