package ch.stnikolauswohlen.santacloud.config.database;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = {"ch.stnikolauswohlen.santacloud.v1.entities.dao", "ch.stnikolauswohlen.santacloud.v1.repositories"},
    entityManagerFactoryRef = "santacloudEntityManager",
    transactionManagerRef = "santacloudTransactionManager"
)
public class ApiSantacloudDbConfig
{
    private final ApiDatabaseConfig apiDatabaseConfig;

    @Autowired
    public ApiSantacloudDbConfig(final ApiDatabaseConfig apiDatabaseConfig)
    {
        this.apiDatabaseConfig = apiDatabaseConfig;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean santacloudEntityManager()
    {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(santacloudDataSource());
        entityManager.setPackagesToScan("ch.stnikolauswohlen.santacloud.v1.entities.dao", "ch.stnikolauswohlen.santacloud.v1.repositories");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", apiDatabaseConfig.getHibernateDdlAuto());
        properties.put("hibernate.dialect", apiDatabaseConfig.getHibernateDialect());
        properties.put("hibernate.ejb.entitymanager_factory_name", "santacloudEntityManager");
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean
    public DataSource santacloudDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(apiDatabaseConfig.getDriverClassName());
        dataSource.setUrl(apiDatabaseConfig.getUrl());
        dataSource.setUsername(apiDatabaseConfig.getUsername());
        dataSource.setPassword(apiDatabaseConfig.getPassword());

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager santacloudTransactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(santacloudEntityManager().getObject());

        return transactionManager;
    }
}
