package be.caly.conf.datasources;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EGSConfiguration {

	@Value("${egs.db.url}")
	private String dbUrl;

	@Value("${egs.db.driver}")
	private String dbDriver;

	@Value("${egs.db.username}")
	private String dbUsername;

	@Value("${egs.db.password}")
	private String dbPassword;

	@Bean(name = "egsEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean egsEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean egsEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		egsEntityManagerFactory.setDataSource(egsDataSource());
		egsEntityManagerFactory.setPackagesToScan(new String[] { "be.caly.*" });
		egsEntityManagerFactory.setPersistenceUnitName("test");

		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.ORACLE);
		egsEntityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);

		return egsEntityManagerFactory;
	}

	@Bean(name = "egsDataSource")
	public DataSource egsDataSource() {
		return DataSourceBuilder.create().url(dbUrl).driverClassName(dbDriver).username(dbUsername).password(dbPassword)
				.build();
	}

	@Bean(name = "transactionManagerEgs")
	public PlatformTransactionManager egsTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager egsTransactionManager = new JpaTransactionManager();
		egsTransactionManager.setEntityManagerFactory(emf);

		return egsTransactionManager;
	}

	@Bean(name = "egsExceptionTranslation")
	public PersistenceExceptionTranslationPostProcessor egsExceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
