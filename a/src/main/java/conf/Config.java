package conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import controller.HomeController;


@ComponentScan(basePackages = { "user" })
@PropertySource("classpath:/db.properties")
@Configuration
public class Config {

	//-----------------------------데이터 접속을 위한 설정-------------------------------------
	@Autowired
	Environment env;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private SqlSession sqlSession;

	@Bean("dataSource")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("jdbc.maxTotal")));
		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("jdbc.maxIdle")));
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(new ClassPathResource("userMapper.xml"));
		return sqlSessionFactory;
	}
	
	/*패턴을 매칭해주는 클래스. 자원(xml; resource)의 값을 얻어온다.   //classpath: 없어도 되네 ?
	sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:spring/mybatis-config.xml"));
	sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("*///dao/*Mapper.xml")); 

	@Bean
	public SqlSessionTemplate sqlSession() {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;		
	}
	//-----------------------------데이터 접속을 위한 설정 끝-------------------------------------
	
	
	// 홈 컨트롤러
	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
	
	//----------------------------- Resolver -------------------------------------

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		return new InternalResourceViewResolver();
	}
	
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
		mappingJackson2JsonView.setContentType("text/html; charset=UTF-8");
		return mappingJackson2JsonView;
	}
	
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);
		return beanNameViewResolver;
	}
	//----------------------------- End of Resolver -------------------------------------	
	
	

}
