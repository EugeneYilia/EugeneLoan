package club.eugeneliu.information.service;

import club.eugeneliu.information.entity.User_optional_info;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public interface IUser_optional_infoService extends IService<User_optional_info> {
    boolean insertUserOptionalInfo(String id_card);
}
