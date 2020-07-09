package com.bnmzy.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bnmzy.mybatisplus.entity.Employee;
import com.bnmzy.mybatisplus.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Author: XF-DD
 * @Date: 2020/7/8 17:17
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void test01(){
        // 查询name不为空的用户，并且邮箱不为空的emp，年龄大于等于12
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("last_name")
                .isNotNull("email")
                .ge("age",23);
        employeeMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test02(){
        // 查询name为Tom的用户
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("last_name","Tom");
        // 查询多个使用list或者map
        Employee employee = employeeMapper.selectOne(wrapper);
        System.out.println(employee);
    }

    @Test
    void test03(){
        // 查询年龄在 18~24 之间的用户
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.between("age",18,24);
        Integer count = employeeMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    void test04(){
        // 模糊查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                // 名字不是 %m%
                .notLike("last_name","m")
                // 左：%Aca  右：Aca%
                .likeRight("email","Aca");

        List<Map<String, Object>> maps = employeeMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test05(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // id在子查询中查出来
        wrapper.inSql("id","select id from employee where id < 4");
        List<Object> objects = employeeMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void test06(){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // 通过id进行排序
        wrapper.orderByDesc("id");
        List<Employee> employees = employeeMapper.selectList(wrapper);
        employees.forEach(System.out::println);
    }
}
