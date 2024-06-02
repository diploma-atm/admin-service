package kz.diploma.admin.service.configuration;

import kz.diploma.adapter.access.AdapterFeignAutoConfiguration;
import kz.diploma.auth.access.AuthAccessAutoConfiguration;
import kz.diploma.library.shared.error_handling.ErrorHandlingAutoConfiguration;
import kz.diploma.library.shared.model.ModelsAutoConfiguration;
import kz.diploma.shared.library.security.SecurityInterceptorConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ModelsAutoConfiguration.class,
        AdapterFeignAutoConfiguration.class,
        AuthAccessAutoConfiguration.class,
        ErrorHandlingAutoConfiguration.class,
        SecurityInterceptorConfiguration.class}
)
public class SecurityConfiguration {
}
