package rnd.mate00.twodatasources.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import rnd.mate00.twodatasources.model1.Book;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"rnd.mate00.twodatasources.model1", "rnd.mate00.twodatasources.repo1"},
        entityManagerFactoryRef = "bookEntityManagerFactory",
        transactionManagerRef = "bookTransactionManager")
public class BookDatasourceConfiguration {

    @Value("${book.datasource.driver-class-name}")
    private String driver;

    @Value("${book.datasource.url}")
    private String url;

    @Value("${book.datasource.username}")
    private String user;

    @Value("${book.datasource.password}")
    private String pass;
    
    @Bean
    @Primary
    public DataSource bookDataSource() {
        System.out.println("Configuring book.datasources");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);

        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(bookDataSource())
                .packages("rnd.mate00.twodatasources.model1", "rnd.mate00.twodatasources.repo1")
                .persistenceUnit("booksPU")
                .build();
    }

    @Bean
    @Primary
    public TransactionManager bookTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(bookDataSource());
        manager.setEntityManagerFactory(bookEntityManagerFactory(builder).getObject());

        return manager;
    }
}
