package com.bnmzy.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: XF-DD
 * @Date: 2020/7/8 11:51
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        /**
         * 3.3.0 后该方法已过时
         * default MetaObjectHandler setFieldValByName
         * (String fieldName, Object fieldVal, MetaObject metaObject)
         * 1.字段名  2.字段值  3. 给哪个数据处理
         */
/*        this.setFieldValByName("createTime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);*/
        /**
         * 下面是新版的方法
         */
        this.strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
//        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
        this.fillStrategy(metaObject,"updateTime",LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }
}

