package club.eugeneliu.trade.service;

import club.eugeneliu.trade.entity.Intend_borrow;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface IIntend_borrowService extends IService<Intend_borrow> {
    int getIntendNumber(String id_card);
    boolean insertIntendBorrow(Intend_borrow intend_borrow);
    Intend_borrow getIntendedLoans(String id_card);
}
