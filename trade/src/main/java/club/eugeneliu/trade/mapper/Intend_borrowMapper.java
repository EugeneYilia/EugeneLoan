package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Intend_borrow;
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
public interface Intend_borrowMapper extends BaseMapper<Intend_borrow> {
    int getIntendNumber(@Param("id_card") String id_card);
    int insertIntendBorrow(Intend_borrow intend_borrow);
    Intend_borrow getIntendedLoans(@Param("id_card") String id_card);
}
