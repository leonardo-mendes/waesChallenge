package configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@EnableJpaRepositories(basePackages = {"repositories"})*/
@EntityScan(basePackages = {"domains"})
@ComponentScan(basePackages = {"controllers", "services"})
public class ApplicationConfig {


}