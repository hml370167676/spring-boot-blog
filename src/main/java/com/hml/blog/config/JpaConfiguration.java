package com.hml.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>Description:(Jpa配置信息)</p> The type Jpa configuration.
 *
 * @author minglu
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.**.repository")
public class JpaConfiguration {

  @Bean
  PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/atp?characterEncoding=utf8");
    dataSource.setUsername("hanminglu");
    dataSource.setPassword("hml188945");

    return dataSource;
  }

  private Properties buildHibernateProperties() {

    Properties hibernateProperties = new Properties();

    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    hibernateProperties.setProperty("hibernate.show_sql", "true");
    hibernateProperties.setProperty("hibernate.use_sql_comments", "false");
    hibernateProperties.setProperty("hibernate.format_sql", "true");
    /**
     * @Document
     *create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
     *create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
     *update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
     *validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
     */
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
    hibernateProperties.setProperty("hibernate.generate_statistics", "false");
    hibernateProperties.setProperty("javax.persistence.validation.mode", "none");

    hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
    hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", "true");

    return hibernateProperties;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan("com.**.entity");
    entityManagerFactoryBean.setJpaProperties(buildHibernateProperties());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
      {
        setDatabase(Database.MYSQL);
      }
    });
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager();
  }

  @Bean
  public TransactionTemplate transactionTemplate() {
    return new TransactionTemplate(transactionManager());
  }


}
