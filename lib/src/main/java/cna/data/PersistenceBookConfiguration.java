package cna.data;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;



@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "cna.data.book",
	entityManagerFactoryRef = "bookEntityManager",
	transactionManagerRef = "bookTransactionManager"
)
@EntityScan("cna.data.model")
@ComponentScan(basePackages = { "cna.data.model.*" })
public class PersistenceBookConfiguration {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix="spring.book-datasource")
	public DataSource bookDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean bookEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(bookDataSource());
		em.setPackagesToScan(new String[] {"cna.data.model"});
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		
		return em;
		
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager bookTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(bookEntityManager().getObject());
		return transactionManager;
	}
}

