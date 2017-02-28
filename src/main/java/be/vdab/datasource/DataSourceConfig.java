package be.vdab.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by Maarten Westelinck on 28/02/2017 for groenetenen.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    DataSource dataSource() {
        return new JndiDataSourceLookup().getDataSource("jdbc/groenetenen");
    }
}
