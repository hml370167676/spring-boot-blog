package com.hml.blog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description:(Jpa配置信息)</p>
 * The type Jpa configuration.
 *
 * @author minglu
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.**.repository")
public class JpaConfiguration {



}
