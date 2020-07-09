package com.bnmzy.mybatisplus.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: XF-DD
 * @Date: 2020/7/9 9:22
 */
public class CodeGenerate {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        // 需要构建一个 代码自动生成器 对象
        AutoGenerator generator = new AutoGenerator();

        // 配置策略

        // 1. 全局配置 com.baomidou.mybatisplus.generator.config.GlobalConfig;
        GlobalConfig globalConfig = new GlobalConfig();
        // 得到项目路径(在系统中路径)
        String property = System.getProperty("user.dir");
        // 生成的文件存放的路径
        globalConfig.setOutputDir(property + "/src/main/java");
        globalConfig.setAuthor("BNMZY");
        globalConfig.setOpen(false);
        // 是否覆盖之前写的
        globalConfig.setFileOverride(false);
        // 设置生成的Service接口名(默认IService)
        globalConfig.setServiceName("%sService");
        // 设置默认的主键生成策略
        globalConfig.setIdType(IdType.ID_WORKER);
        // 设置日期格式，仅显示天
        globalConfig.setDateType(DateType.ONLY_DATE);
        // 设置Swagger
        globalConfig.setSwagger2(true);

        // 将全局配置加入generator
        generator.setGlobalConfig(globalConfig);

        // 2. 设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://120.79.18.7:32553/test_xf?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dataSourceConfig.setUsername("sms");
        dataSourceConfig.setPassword("Yeion#187.top");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);
        generator.setDataSource(dataSourceConfig);

        // 3. 包的配置
        PackageConfig packageConfig = new PackageConfig();
        // 在哪个包下
        packageConfig.setModuleName("generate");
        // 上面的包在哪个包下
        packageConfig.setParent("com.bnmzy.mybatisplus");
        // 生成的各种东西的文件夹名字
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        generator.setPackageInfo(packageConfig);

        // 4. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 命名规则 ： 下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // localhost:8080/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);


        // 设置要映射的表名字
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude("user");
        // 逻辑删除的字段
        strategy.setLogicDeleteFieldName("deleted");
        // 版本的字段
        strategy.setVersionFieldName("version");

        // 自动填充配置
        TableFill create = new TableFill("create", FieldFill.INSERT);
        TableFill update = new TableFill("update", FieldFill.INSERT_UPDATE);
        List<TableFill> list = Arrays.asList(create, update);
        strategy.setTableFillList(list);


        generator.setStrategy(strategy);
        generator.setTemplateEngine(new VelocityTemplateEngine());
        generator.execute();

    }
}
