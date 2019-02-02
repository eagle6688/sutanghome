package com.sutanghome.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = { "com.sutanghome.dao.mapper" }, sqlSessionTemplateRef = "sqlSessionTemplate")
public class DatabaseConfiguration {
	@Bean
	@ConfigurationProperties("mysql.sutanghome")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
		VFS.addImplClass(SpringBootVFS.class);
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("mybatis-config.xml");
		Resource[] mappeResources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(resources[0]);
		sqlSessionFactoryBean.setMapperLocations(mappeResources);
		return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}