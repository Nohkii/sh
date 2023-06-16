package config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

import kr.co.project.member.LoginInterceptor;

@Configuration 
// 해당 클래스가 스프링 설정 클래스임을 나타냄
@EnableWebMvc
// 스프링 MVC 설정을 활성화
@ComponentScan(basePackages = {"kr.co.project"})
// 지정된 패키지 및 하위 패키지에서 스프링 컴포넌트를 찾아 빈으로 등록
@MapperScan(basePackages = {"kr.co.project"}, annotationClass = Mapper.class)
//  MyBatis 매퍼 인터페이스를 찾아 빈으로 등록
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		 // 뷰 리졸버를 등록하는 메소드
		reg.jsp("/WEB-INF/jsp/", ".jsp");
		// 포워딩하는 설정 - jsp를 처리하는 뷰 리졸버를 등록
	}
	
	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/project");
		dataSource.setUsername("testuser");
		dataSource.setPassword("test1234");
		return dataSource;
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sst() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	// 정적페이지
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index.do").setViewName("index");
	}
	
	// 정적리소스(html, css, js, 이미지....)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// 파일첨부
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(1024*1024*3);
		return resolver;
	}
	
	// 트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	// 인터셉터
	// 주석처리하면 로그인하고 들어가야하는 페이지들도 다 들어가짐
//	@Bean
//	public LoginInterceptor interceptor() {
//		return new LoginInterceptor();
//	}
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry reg) {
//		reg.addInterceptor(interceptor())
//			.addPathPatterns("/board/notice/*.do")
//			.addPathPatterns("/member/mypage.do")
//			.excludePathPatterns("/board/notice/index.do")
//			;
//	}
	
	
}
