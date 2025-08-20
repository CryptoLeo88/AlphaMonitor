package com.block;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Main {

    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

//        Geth geth = Geth.build(new HttpService("http://61.132.225.58:8547"));

        // 数据源
        DataSourceConfig sourceConfig = new DataSourceConfig();

        sourceConfig.setDbType(DbType.MYSQL);
        sourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/personal_info?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=Asia/Shanghai");
        sourceConfig.setUsername("root");
        sourceConfig.setPassword("123456");
        sourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        autoGenerator.setDataSource(sourceConfig);



        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor("lrfc");
        globalConfig.setServiceName("%sService");
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        //包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.block");
        packageConfig.setModuleName(null);
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("serviceImpl");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude("airdrop".split(","));

        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
