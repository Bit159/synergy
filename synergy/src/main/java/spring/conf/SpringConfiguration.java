package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfiguration {
   
   @Bean
   public BasicDataSource dataSource() {
	  BasicDataSource basicDataSource = new BasicDataSource();
	  basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	  basicDataSource.setUrl("jdbc:mysql://security-db.ci3ttblcjdxh.ap-northeast-2.rds.amazonaws.com:3306/db?useSSL=false");
	  basicDataSource.setUsername("jpcnani");
	  basicDataSource.setPassword("bitcamp159");
	  basicDataSource.setMaxTotal(20);
	  basicDataSource.setMaxIdle(3);
      
      return basicDataSource;
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