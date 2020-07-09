package com.bnmzy.mybatisplus.generate.service.impl;

import com.bnmzy.mybatisplus.generate.entity.User;
import com.bnmzy.mybatisplus.generate.mapper.UserMapper;
import com.bnmzy.mybatisplus.generate.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BNMZY
 * @since 2020-07-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
