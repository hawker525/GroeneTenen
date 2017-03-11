package be.vdab.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

/**
 * Created by maarten on 11/03/2017.
 */
public class RegisterSecurityFilter extends AbstractSecurityWebApplicationInitializer{

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext context) {
        super.insertFilters(context, new CharacterEncodingFilter("UTF-8"));
    }

}
