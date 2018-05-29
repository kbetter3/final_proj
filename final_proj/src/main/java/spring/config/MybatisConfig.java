package spring.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages= {"spring.mybatis"})
public class MybatisConfig {
	// SqlSessionFactoryBean		mybatis의 모든 설정을 처리할 파일
	// SqlSessionTemplate			추가로 작성한 SQL 명령파일을 실제 코드로 바꾸어 실행해주는 도구
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws IOException {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
		// [1]DB연결은 누가하는가
		bean.setDataSource(dataSource);
		
		// [2] mybatis 설정 파일의 위치가 어디인가
		bean.setConfigLocation(applicationContext.getResource("classpath:/spring/mybatis/mybatis-config.xml"));
		
		// [3] mybatis SQL파일(Mapper)의 위치가 어디인가
		bean.setMapperLocations(applicationContext.getResources("classpath:/spring/mybatis/*-mapper.xml"));
		
		return bean;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		
		return template;
	}
}
