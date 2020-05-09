package com.github.yuanrw.im.rest.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yuanrw.im.common.domain.po.User;
import com.github.yuanrw.im.common.domain.po.UserDetail;

/**
 * Date: 2019-02-09
 * Time: 19:11
 *
 * @author yrw
 */

public interface UserMapper extends BaseMapper<User> {



    public UserDetail getUserById(Long userId);
}