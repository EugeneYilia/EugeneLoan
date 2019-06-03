package club.eugeneliu.trade.service;

import club.eugeneliu.trade.entity.Borrower_account;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface IBorrower_accountService extends IService<Borrower_account> {
    boolean insertBorrower(Borrower_account borrower_account);
    Double getLimit(String id_card);
}
