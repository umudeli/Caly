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
 *
 * Root configuration of Caly Application
 * @author delphine.ngendahayo
 *
 */
@Configuration
public class AppConfiguration {
	
	
	@Bean 
	public static PropertyPlaceholderConfigurer properties() {

	    PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	    ClassPathResource[] resources = new ClassPathResource[ ] {
	        new ClassPathResource("application.properties")
	    };
	    ppc.setLocations( resources );
	    ppc.setIgnoreUnresolvablePlaceholders( true );
	    ppc.setSearchSystemEnvironment(true);
	    return ppc;
	}
	

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
