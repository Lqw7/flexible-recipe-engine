package com.qiwei.engine.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.qiwei")
@SpringBootApplication
@MapperScan("com.qiwei.engine.mapper")
@EnableScheduling
@EnableAsync
public class EngineApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EngineApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EngineApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Successful start!!");
        LOG.info("Address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
