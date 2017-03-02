package be.vdab.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Maarten Westelinck on 2/03/2017 for groenetenen.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
public @interface ModifyingTransactionalServiceMethod {
}
