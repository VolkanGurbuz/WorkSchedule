package dev.volkangurbuz.workschedule.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "dev.volkangurbuz.workschedule.repositories")
@ComponentScan(basePackages = {"dev.volkangurbuz.workschedule"})
public class ElasticConfiguration {}
