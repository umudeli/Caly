package be.caly.conf.datasources;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RepConfiguration {

	@Value("${rep.db.url}")
	private String dbUrl;

	@Value("${rep.db.driver}")
	private String dbDriver;

	@Value("${rep.db.username}")
	private String dbUsername;

	@Value("${rep.db.password}")
	private String dbPassword;

	@Bean(name = "repEntityManager")
	public LocalContainerEntityManagerFactoryBean repEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean repEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		repEntityManagerFactory.setDataSource(repDataSource());
		repEntityManagerFactory.setPackagesToScan(new String[] { "be.caly.*" });
		repEntityManagerFactory.setPersistenceUnitName("test1");

		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.ORACLE);
		repEntityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);

		return repEntityManagerFactory;
	}

	@Bean(name = "repDataSource")
	public DataSource repDataSource() {
		return DataSourceBuilder.create().url(dbUrl).driverClassName(dbDriver).username(dbUsername).password(dbPassword)
				.build();
	}

	@Bean(name = "transactionManagerRep")
	public PlatformTransactionManager repTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager repTransactionManager = new JpaTransactionManager();
		repTransactionManager.setEntityManagerFactory(emf);

		return repTransactionManager;
	}

	@Bean(name = "repExceptionTranslation")
	public PersistenceExceptionTranslationPostProcessor repExceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
