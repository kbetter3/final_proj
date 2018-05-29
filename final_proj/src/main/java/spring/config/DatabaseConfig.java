package spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource(value="file:src/main/webapp/WEB-INF/properties/db.properties")
public class DatabaseConfig {
	// DataSource, JdbcTemplate과 같은 Database 도구를 bean으로 정의
	
	// property를 사용하는 방법
	// [1] @Value를 잉ㅇ하는 방법
//	@Value("database.driver")
//	private String driver;
	// [2] Environment를 이용하는 방법
	@Autowired
	private Environment env;
	
	// <bean>
	@Bean
	// 반드시 public 만들객체의자료형 beanID(필요한 property)
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		
		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("database.maxTotal")));
		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("database.maxIdle")));
		dataSource.setMaxWaitMillis(Integer.parseInt(env.getProperty("database.maxWaitMillis")));
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);
		
		return template;
	}
}
