package com.mrcoder.sbmpmultidbdruid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrcoder.sbmpmultidbdruid.entity.Order;
import com.mrcoder.sbmpmultidbdruid.mapper.db2.OrderMapper;
import com.mrcoder.sbmpmultidbdruid.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrderList() {
        return orderMapper.selectList(null);
    }

    @Override
    public BigDecimal getOrderPriceByUserId(Integer userId) {
        return orderMapper.getPriceByUserId(userId);
    }
}