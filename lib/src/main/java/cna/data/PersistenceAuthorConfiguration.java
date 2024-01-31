package cna.data;

import javax.sql.DataSource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;



@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "cna.data.author",
	entityManagerFactoryRef = "authorEntityManager",
	transactionManagerRef = "authorTransactionManager"
)
public class PersistenceAuthorConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix="spring.author-datasource")
	public DataSource authorDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean authorEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(authorDataSource());
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
	public PlatformTransactionManager authorTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(authorEntityManager().getObject());
		return transactionManager;
	}
}

