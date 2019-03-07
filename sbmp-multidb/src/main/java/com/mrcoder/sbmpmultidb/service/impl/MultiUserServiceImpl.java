package com.mrcoder.sbmpmultidb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mrcoder.sbmpmultidb.entity.MultiUser;
import com.mrcoder.sbmpmultidb.mapper.MultiUserMapper;
import com.mrcoder.sbmpmultidb.service.IMultiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrcoder
 * @since 2019-03-07
 */
@Service
@DS("db1")
public class MultiUserServiceImpl extends ServiceImpl<MultiUserMapper, MultiUser> implements IMultiUserService {
    @DS("db2")
    @Override
    public void addUser(MultiUser user) {
        baseMapper.insert(user);
    }
}
