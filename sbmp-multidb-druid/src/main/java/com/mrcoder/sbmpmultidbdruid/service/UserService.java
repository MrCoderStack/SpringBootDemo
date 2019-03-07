package com.mrcoder.sbmpmultidbdruid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrcoder.sbmpmultidbdruid.entity.User;
import java.math.BigDecimal;
import java.util.List;

/**
 *  服务类
 */
public interface UserService extends IService<User> {
    List<User> getUserList();

    BigDecimal getOrderPriceByUserId(Integer userId);
}