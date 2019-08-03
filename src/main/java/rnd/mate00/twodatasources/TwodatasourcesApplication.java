package rnd.mate00.twodatasources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {  DataSourceAutoConfiguration.class })
public class TwodatasourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwodatasourcesApplication.class, args);
	}

}
/*
@Profile("!test")          // 1
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "au.com.myblog.dao", entityManagerFactoryRef = "entityManager", transactionManagerRef = "transactionManager")        // 2
public class PrimaryMysqlDBConfiguration {
	@Bean(name = "dataSource")      // 3
	@Primary
	@ConfigurationProperties(prefix = "primary.datasource.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	@PersistenceContext(unitName = "primary")   // 4
	@Primary
	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mysqlDataSource()).persistenceUnit("primary").properties(jpaProperties())
				.packages("au.com.myblog.domain").build();
	}
	private Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.ejb.naming_strategy", new SpringNamingStrategy());
		return props;
	}
}

@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "au.com.myblog.dao", entityManagerFactoryRef = "secondaryMySqlEntityManager", transactionManagerRef = "secondaryTransactionManager")
public class SecondaryMysqlDBConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "secondary.datasource.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	@PersistenceContext(unitName = "secondary")
	@Bean(name = "secondaryMySqlEntityManager")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return  builder.dataSource(mysqlDataSource()).properties(jpaProperties()).persistenceUnit("secondary").packages("au.com.myblog.domain").build();
	}
	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(mySqlEntityManagerFactory(builder).getObject());
		tm.setDataSource(mysqlDataSource());
		return tm;
	}
	private Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<>();
		// naming strategy to put underscores instead of camel case
		// as per auto JPA Configuration
		props.put("hibernate.ejb.naming_strategy", new SpringNamingStrategy());
		props.put("hibernate.hbm2ddl.auto", "update");
		return props;
	}
	*/