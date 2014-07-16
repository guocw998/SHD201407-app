package com.urely.shd2014;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * 世界是美好的：加载到hibernate.properties中属性，在factory.afterPropertiesSet()后都神奇的生效了！！！
 * */
@Configuration
@EnableJpaRepositories(basePackages = "com.urely.shd2014.dao")
@EnableTransactionManagement
public class JPAMySQLConfiguration {
	private static final Logger logger = Logger
			.getLogger(JPAMySQLConfiguration.class);

	/**
	 * com.alibaba.druid.pool.DruidDataSource
	 * */
	// @Bean
	// public DataSource dataSource() throws SQLException {
	//
	// EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	// return builder.setType(EmbeddedDatabaseType.H2).build();
	// }

//	@Bean
//	public DataSource dataSource() throws SQLException {
//		logger.info("init DataSource !!!!");
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		return builder.setType(EmbeddedDatabaseType.H2).build();
//	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaProperties(jpaProperties);
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.urely.shd2014.entity");
//		factory.setDataSource(dataSource());
		logger.info("Before LocalContainerEntityManagerFactoryBean.afterPropertiesSet():::"+factory.getJpaPropertyMap());
		factory.afterPropertiesSet();
		logger.info("After LocalContainerEntityManagerFactoryBean.afterPropertiesSet():::"+factory.getJpaPropertyMap());

		return factory.getObject();
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}