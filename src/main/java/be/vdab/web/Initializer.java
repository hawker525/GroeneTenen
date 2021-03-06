package be.vdab.web;

import be.vdab.aop.AOPConfig;
import be.vdab.datasource.DataSourceConfig;
import be.vdab.mail.MailConfig;
import be.vdab.repositories.RepositoriesConfig;
import be.vdab.restclients.RestClientsConfig;
import be.vdab.restservices.RestControllersConfig;
import be.vdab.security.SecurityConfig;
import be.vdab.services.ServicesConfig;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AOPConfig.class,SecurityConfig.class, MailConfig.class, RestClientsConfig.class, RepositoriesConfig.class, ServicesConfig.class, DataSourceConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{RestControllersConfig.class, ControllersConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new OpenEntityManagerInViewFilter()};
    }
}
