package com.bnmzy.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: XF-DD
 * @Date: 2020/7/8 10:20
 */

/**
 * 1. 与表名一样
 * 2. 字段一致
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @TableId(type = IdType.AUTO)
    Integer id;
    String lastName;
    String email;
    String gender;
    Integer age;

    // 表示在插入时自行填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 表示在插入和更新时自行填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // 表示这是一个乐观锁
    @Version
    private Integer version;

    // 逻辑删除
    @TableLogic
    private Integer deleted;

}
