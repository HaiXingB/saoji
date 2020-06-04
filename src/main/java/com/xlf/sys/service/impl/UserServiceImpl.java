package com.xlf.sys.service.impl;

import com.xlf.sys.domain.User;
import com.xlf.sys.mapper.UserMapper;
import com.xlf.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author saoji
 * @since 2020-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
