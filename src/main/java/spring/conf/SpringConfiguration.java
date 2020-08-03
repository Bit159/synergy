package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfiguration {
	
//	@Bean
//	public DriverManagerDataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://security-db.ci3ttblcjdxh.ap-northeast-2.rds.amazonaws.com:3306/db?serverTimezone=Asia/Seoul&useSSL=false");
//		//WARN: Establishing SSL connection without server's identity verification is not recommended. 이 경고 없애기 위해 ?serverTimezone=Asia/Seoul&useSSL=false 를 쓴다.
//		dataSource.setUsername("jpcnani");
//		dataSource.setPassword("bitcamp159");
//		
//		return dataSource;
//	}
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://security-db.ci3ttblcjdxh.ap-northeast-2.rds.amazonaws.com:3306/db?serverTimezone=Asia/Seoul&useSSL=false");
		dataSource.setUsername("jpcnani");
		dataSource.setPassword("bitcamp159");
		dataSource.setMaxTotal(20);
		dataSource.setMaxIdle(3);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:spring/mybatis-config.xml"));

		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:*/dao/*Mapper.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
