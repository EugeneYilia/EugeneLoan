package club.eugeneliu.information.mapper;

import club.eugeneliu.information.entity.User_optional_info;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface User_optional_infoMapper extends BaseMapper<User_optional_info> {
    int insertUserOptionalInfo(@Param("id_card") String id_card);
}
