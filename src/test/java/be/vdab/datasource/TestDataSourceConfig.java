package be.vdab.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Maarten Westelinck on 2/03/2017 for groenetenen.
 */
@Configuration
public class TestDataSourceConfig {

    @Bean
    DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql://localhost/groenetenen?useSSL=false", "cursist", "cursist");
    }
}
