package cn.itcast.mp;

import cn.itcast.mp.injectors.MysqlInjector;
import cn.itcast.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@MapperScan("cn.itcast.mp.mapper") //设置mapper接口的扫描包
@Configuration
public class MybatisPlusConfig {
//    @Bean
//    public PaginationInterceptor paginationInterceptor(){
//        return new PaginationInterceptor();
//    }

    /**
     * 自定义拦截器 */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();

        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser()); //全表更新、删除的阻断器
        sqlExplainInterceptor.setSqlParserList(sqlParserList);
        return  sqlExplainInterceptor;
    }
    @Bean
    public MysqlInjector mysqlInjector(){
        return new MysqlInjector();
    }
}
