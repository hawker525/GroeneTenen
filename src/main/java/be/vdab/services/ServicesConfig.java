package be.vdab.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
@Configuration
@ComponentScan
@EnableScheduling
@EnableTransactionManagement
public class ServicesConfig {
}
