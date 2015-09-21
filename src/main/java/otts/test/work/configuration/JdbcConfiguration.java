package otts.test.work.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by alex on 14.09.2015.<br/>
 * TODO comment me
 */
@Configuration
public class JdbcConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                //.url("jdbc:h2:tcp://localhost/E:/work/activiti-db/activiti")
                .url("jdbc:h2:mem:activiti")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
