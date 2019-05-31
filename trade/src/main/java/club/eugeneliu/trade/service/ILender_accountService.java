package club.eugeneliu.trade.service;

import club.eugeneliu.trade.entity.Lender_account;
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
public interface ILender_accountService extends IService<Lender_account> {
    boolean insertLender(Lender_account lender_accountnt);
}
