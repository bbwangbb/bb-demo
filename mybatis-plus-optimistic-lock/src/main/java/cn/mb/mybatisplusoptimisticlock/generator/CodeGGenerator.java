//package cn.mb.mybatisplusoptimisticlock.generator;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.Scanner;
//
///**
// * 车G时代项目 代码生成器
// *
// * @author  guohaibin
// * @date  2020/10/17
// */
//public class CodeGGenerator {
//    //--------------------数据库相关配置---------------------
//    //  TODO 数据库url
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false";
//    //  TODO 账号
//    private static final String USERNAME = "root";
//    //  TODO 密码
//    private static final String PASSWORD = "root";
//    //  连接驱动
//    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
//
//    //--------------------全局相关配置-----------------------
//    //  文件输出路径
//    private static final String PROJECT_PATH = System.getProperty("user.dir");
//    //  作者名
//    private static final String AUTHOR = "bb";
//
//    //--------------------包相关配置-----------------------
//    //  包名
//    private static final String BASE_PACKAGE = "cn.mb.mybatisplusoptimisticlock";
//
//    //--------------------策略相关配置-----------------------
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//    /**
//     * RUN THIS
//     */
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator autoGenerator = new AutoGenerator();
//
//        /**
//         * 数据源配置
//         */
//        autoGenerator.setDataSource(new DataSourceConfig()
//                .setUrl(DB_URL)
//                .setUsername(USERNAME)
//                .setPassword(PASSWORD)
//                .setDriverName(DRIVER_NAME)
////                .setTypeConvert(new MySqlTypeConvert() {//  会不会添加字段的@TableField注解
////                    // 自定义数据库表字段类型转换【可选】
////                    //@Override
////                    //public DbColumnType processTypeConvert(String fieldType) {
////                    //System.out.println("转换类型：" + fieldType);
////                    // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
////                    //    return DbColumnType.BOOLEAN;
////                    // }
////                    //return super.processTypeConvert(fieldType);
////                    //}
////                })
//        );
//        /**
//         * 全局配置
//         */
//        autoGenerator.setGlobalConfig(new GlobalConfig()
//                //输出目录
//                .setOutputDir( PROJECT_PATH + "/src/main/java")
//                //是否覆盖文件
//                .setFileOverride(false)
//                // 开启 activeRecord 模式 - 实体类继承model
////                .setActiveRecord(true)
//                // XML 二级缓存
//                .setEnableCache(false)
//                // XML ResultMap -  给xml生成默认的ResultMap
//                .setBaseResultMap(true)
//                // XML columnList - 给xml生成列
////                .setBaseColumnList(true)
//                //生成后打开文件夹
//                .setOpen(false)
//                //作者
//                .setAuthor(AUTHOR)
//                // 自定义文件命名，注意 %s 会自动填充表实体属性！
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper")
//                .setServiceName("%sService")
//                .setServiceImplName("%sServiceImpl")
//                .setControllerName("%sController")
//        );
//
//        /**
//         * 包配置
//         */
//        PackageConfig packageConfig = new PackageConfig()
//                .setParent(BASE_PACKAGE)
//                .setEntity("dao.entity")
////                .setModuleName(scanner("模块名"))
//                .setMapper("dao")
//                .setService("service")
//                .setServiceImpl("service.impl")
//                .setController("controller")
//                .setXml("dao.mapper")
//                ;
//         autoGenerator.setPackageInfo(packageConfig);
//
//
//        /**
//         * 自定义配置
//         */
//        // 注入自定义配置
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        autoGenerator.setCfg(injectionConfig);
//
////        //  模板
////        autoGenerator.setTemplate(
////                new TemplateConfig()
////                .setEntity("templates/entity.java")
////                .setController("templates/controller.java")
////        );
//
//        /**
//         * 策略配置
//         */
//        autoGenerator.setStrategy(new StrategyConfig()
//                        //表名生成策略
//                        .setNaming(NamingStrategy.underline_to_camel)
//                        //数据库表字段映射到实体的命名策略
//                        .setColumnNaming(NamingStrategy.underline_to_camel)
//                        //表前缀
////                        .setTablePrefix(TABLE_PREFIX)
//                        //是否为lombok模型
//                        .setEntityLombokModel(true)
//                        //需要排除的表
//                        //.setExclude(EXCLUDE_TABLE)
//                        .setControllerMappingHyphenStyle(true)
//                        .setInclude(scanner("表名,多个使用,分割").split(","))
//                        //设置controller类型
//                        .setRestControllerStyle(true)
//
//               /* 基类相关设置
//                .setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity")
//                .setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController")
//                .setSuperServiceClass()
//                .setSuperServiceImplClass()
//                .setSuperMapperClass()*/
//        );
//
//        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
//        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
//        autoGenerator.execute();
//    }
//}
