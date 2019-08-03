package rnd.mate00.twodatasources.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import rnd.mate00.twodatasources.model2.Role;
import rnd.mate00.twodatasources.repo2.RoleRepository;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = { Role.class, RoleRepository.class },
        entityManagerFactoryRef = "securityEntityManagerFactory",
        transactionManagerRef = "securityTransactionManager"
)
public class SecurityDatasourceConfiguration {

    @Value("${security.datasource.driver-class-name}")
    private String driver;

    @Value("${security.datasource.url}")
    private String url;

    @Value("${security.datasource.username}")
    private String user;

    @Value("${security.datasource.password}")
    private String pass;

    @Bean
    @ConfigurationProperties(prefix = "security.datasource")
    public DataSource securityDataSource() {
        System.out.println("Configuring security.datasourcec");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(securityDataSource())
                .packages(Role.class, RoleRepository.class)
                .persistenceUnit("securityPU")
                .build();
    }

    @Bean
    public TransactionManager securityTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(securityDataSource());
        manager.setEntityManagerFactory(securityEntityManagerFactory(builder).getObject());

        return manager;
    }

}
