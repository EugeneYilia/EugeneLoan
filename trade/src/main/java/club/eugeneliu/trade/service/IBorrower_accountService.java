package club.eugeneliu.trade.service;

import club.eugeneliu.trade.entity.Borrower_account;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface IBorrower_accountService extends IService<Borrower_account> {
//    boolean insertBorrower(Borrower_account borrower_account);

    Double getLimit(String id_card);

    Borrower_account getAllInformation(String id_card);

    Double getAccountBalance(String id_card);

    boolean updateAccountBalance(String id_card, double new_balance);

    String getFundsAccount(String id_card);

    boolean updateAvailableLimit(String id_card, double available_limit);
}
