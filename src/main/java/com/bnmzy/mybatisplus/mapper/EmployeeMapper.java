package com.bnmzy.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bnmzy.mybatisplus.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * @Author: XF-DD
 * @Date: 2020/7/8 10:21
 */
// 对应的 Mapper上面集成基本的类 BaseMapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 此时所有的CRUD(基本的)已经完成
     */
}
