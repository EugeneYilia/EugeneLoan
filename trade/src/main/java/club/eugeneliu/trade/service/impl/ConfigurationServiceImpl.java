package club.eugeneliu.trade.service.impl;

import club.eugeneliu.trade.entity.Configuration;
import club.eugeneliu.trade.mapper.ConfigurationMapper;
import club.eugeneliu.trade.service.IConfigurationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
@Service
public class ConfigurationServiceImpl extends ServiceImpl<ConfigurationMapper, Configuration> implements IConfigurationService {

}
