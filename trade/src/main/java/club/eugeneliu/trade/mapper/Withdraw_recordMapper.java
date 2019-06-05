package club.eugeneliu.trade.mapper;

import club.eugeneliu.trade.entity.Withdraw_record;
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
public interface Withdraw_recordMapper extends BaseMapper<Withdraw_record> {
    int insertRecord(Withdraw_record withdraw_record);

}
