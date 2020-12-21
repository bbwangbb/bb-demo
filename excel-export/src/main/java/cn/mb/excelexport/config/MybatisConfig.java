package cn.mb.excelexport.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     mybatis相关配置
 * </p>
 *
 * @author kuangtengfei
 * @since 2020-08-19
 */
@Configuration
public class MybatisConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //  TODO    若要测试更大，可以在这里设置
        paginationInterceptor.setLimit(10000000);
//        paginationInterceptor.setLimit(50);
        return paginationInterceptor;
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
