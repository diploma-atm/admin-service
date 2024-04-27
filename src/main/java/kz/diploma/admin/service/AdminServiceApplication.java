package kz.diploma.admin.service;

import kz.diploma.adapter.access.AdapterFeignAutoConfiguration;
import kz.diploma.library.shared.model.ModelsAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import(value = {ModelsAutoConfiguration.class, AdapterFeignAutoConfiguration.class})
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
