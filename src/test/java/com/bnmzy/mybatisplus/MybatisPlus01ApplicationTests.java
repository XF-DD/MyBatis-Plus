package com.bnmzy.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bnmzy.mybatisplus.entity.Employee;
import com.bnmzy.mybatisplus.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlus01ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
    }

    /**
     *  1. 继承了BaseMapper，所有的方法都来自父类
     *  2. 也可以编写自己的扩展方法
     */
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void testList(){
        // 参数是一个Wrapper ， 条件构造器， 这里先不用，设为null
        // 查询全部员工
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }

    @Test
    void testInsert(){
        Employee employee = new Employee();
        employee.setLastName("FF");
        employee.setAge(24);
        employee.setEmail("FF@163.com");
        employee.setGender("0");
//        employee.setId(5);
        // 自动生成Id
        int insert = employeeMapper.insert(employee);
        System.out.println(insert);
        // 自动生成的Id会写到该对象
        System.out.println(employee);
    }

    // 测试更新
    @Test
    void testUpdate(){
        Employee employee = new Employee();
        employee.setId(5);
        // 通过条件自动拼接动态sql
        employee.setLastName("MR");
        // 传入的对象是 T ,即mapper传入的泛型
        int result = employeeMapper.updateById(employee);
        System.out.println(result);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker(){
        Employee employee = employeeMapper.selectById(6);
        employee.setLastName("Acai");
        employee.setEmail("Acai@163.com");
        employeeMapper.updateById(employee);
    }

    //测试乐观锁
    @Test
    void testOptimisticLockerFail(){
        Employee employee = employeeMapper.selectById(8);
        employee.setLastName("Em1");
        employee.setEmail("Em1@163.com");

        Employee employee2 = employeeMapper.selectById(8);
        employee2.setLastName("Em2");
        employee2.setEmail("Em2@163.com");
        employeeMapper.updateById(employee2);

        // 此时触发乐观锁，失败，可用自旋锁尝试多次提交
        employeeMapper.updateById(employee);
    }

    @Test
    void testSelectBuId(){
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee);
    }

    // 批量查询
    @Test
    void testSelectByBatchId(){
        List<Employee> employees = employeeMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        employees.forEach(System.out::println);
    }

    // 条件查询之一：按map操作
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询
        map.put("last_name","Acai");
        map.put("email","Acai@163.com");
        List<Employee> employees = employeeMapper.selectByMap(map);
        employees.forEach(System.out::println);
    }

    @Test
    void testPage(){
        // 第1页，一页面大小是3
        Page<Employee> employeePage = new Page<>(1, 3);
        employeeMapper.selectPage(employeePage,null);

        // 获得结果
        employeePage.getRecords().forEach(System.out::println);
        // 总记录数
        System.out.println(employeePage.getTotal());
        // 当前页数
        System.out.println(employeePage.getCurrent());
        // 共多少页
        System.out.println(employeePage.getSize());
    }

    // 测试删除
    @Test
    void testDeleteById(){
        employeeMapper.deleteById(1);
    }

    // 批量删除
    @Test
    void testDeleteBatchId(){
        employeeMapper.deleteBatchIds(Arrays.asList(10,11));
    }

    // map删除
    @Test
    void testDeleteMap(){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("last_name","FF");
        employeeMapper.deleteByMap(stringObjectHashMap);
    }
}
