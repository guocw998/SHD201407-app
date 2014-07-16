package com.urely.shd2014;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.urely.test")
@EnableTransactionManagement
@PropertySource("classpath:/com/urely/superweb/demo2/database.properties")
public class TestDatabaseConfig {
	private static final Logger logger=Logger.getLogger(TestDatabaseConfig.class);
	@Autowired Environment env;
	
	/**
	 * com.alibaba.druid.pool.DruidDataSource
	 * */
	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		//ds.configFromPropety(properties);
		ds.init();
		return ds;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.urely.test");
		factory.setDataSource(dataSource());
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
