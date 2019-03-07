package com.mrcoder.sbmpmultidbdruid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrcoder.sbmpmultidbdruid.entity.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *  服务类
 */
public interface OrderService extends IService<Order> {
    List<Order> getOrderList();
    BigDecimal getOrderPriceByUserId(Integer userId);
}