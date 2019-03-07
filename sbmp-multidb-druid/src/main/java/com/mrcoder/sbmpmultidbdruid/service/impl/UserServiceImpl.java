package com.mrcoder.sbmpmultidbdruid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrcoder.sbmpmultidbdruid.config.DBTypeEnum;
import com.mrcoder.sbmpmultidbdruid.config.DataSourceSwitch;
import com.mrcoder.sbmpmultidbdruid.entity.User;
import com.mrcoder.sbmpmultidbdruid.mapper.db2.OrderMapper;
import com.mrcoder.sbmpmultidbdruid.mapper.db1.UserMapper;
import com.mrcoder.sbmpmultidbdruid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 *  服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @DataSourceSwitch(DBTypeEnum.db2)
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @Override
    //@DataSourceSwitch(DBTypeEnum.db2)
    public BigDecimal getOrderPriceByUserId(Integer userId) {
        return orderMapper.getPriceByUserId(userId);
    }
}