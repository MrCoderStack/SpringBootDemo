package com.mrcoder.sbmpmultidb.service;

import com.mrcoder.sbmpmultidb.entity.MultiUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mrcoder
 * @since 2019-03-07
 */
public interface IMultiUserService extends IService<MultiUser> {
    void addUser(MultiUser user);
}
