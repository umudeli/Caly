	package be.caly.conf;

	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
	import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.context.annotation.Primary;
	import org.springframework.core.io.ClassPathResource;
	/**
	 * Root configuration of Caly Application
	 * @author delphine.ngendahayo
	 *
	 */
	@Configuration
	@PropertySource("application-${spring.profiles.active}.properties")
	public class AppConfiguration {


		@Bean
		@Primary
		public DataSource dataSource(){
		   return DataSourceBuilder.create()
					.url("jdbc:hsqldb:mem:testdb")
					.driverClassName("org.hsqldb.jdbcDriver")
					.username("sa")
					.password("sa")
					.build();
		}


	}
