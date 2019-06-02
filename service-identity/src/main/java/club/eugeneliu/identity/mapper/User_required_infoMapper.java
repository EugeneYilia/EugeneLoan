package club.eugeneliu.identity.mapper;

import club.eugeneliu.identity.entity.User_required_info;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Component
public interface User_required_infoMapper extends BaseMapper<User_required_info> {
    User_required_info checkIdentity(@Param("phone_number") String phoneNumber,@Param("password") String password);

}
