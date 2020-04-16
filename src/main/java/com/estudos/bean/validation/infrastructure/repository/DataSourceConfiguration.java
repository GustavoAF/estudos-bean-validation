package com.estudos.bean.validation.infrastructure.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/*
 * Data Source padrão
 */
@Configuration
public class DataSourceConfiguration {
	
	@Autowired
	ConfigurationDataBase configurationnBd;

    @Bean
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		
		return DataSourceBuilder.create()
					.type(HikariDataSource.class)
					.url(configurationnBd.getDataSourceUrl())
					.username(configurationnBd.getDataSourceUser())
					.password(configurationnBd.getDataSourcePassword())
					.build();
		
	}
}
