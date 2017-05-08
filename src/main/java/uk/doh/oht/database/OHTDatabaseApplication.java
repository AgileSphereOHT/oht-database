package uk.doh.oht.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointMBeanExportAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by peterwhitehead on 04/05/2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "uk.doh.oht" })
@Slf4j
@EnableAutoConfiguration(exclude = {EndpointMBeanExportAutoConfiguration.class})
public class OHTDatabaseApplication {
    public static void main(final String... args) throws Exception {
        log.info("Starting oht-database");
        SpringApplication.run(OHTDatabaseApplication.class, args);
    }
}
