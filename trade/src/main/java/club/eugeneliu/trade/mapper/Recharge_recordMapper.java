package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Recharge_record;
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
public interface Recharge_recordMapper extends BaseMapper<Recharge_record> {
    int insertRecord(Recharge_record recharge_record);

}
