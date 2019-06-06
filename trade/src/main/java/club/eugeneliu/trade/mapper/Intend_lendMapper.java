package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Intend_lend;
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
public interface Intend_lendMapper extends BaseMapper<Intend_lend> {
    Double getForzenMoney(@Param("id_card") String id_card);
}
