package com.qiwei.engine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.qiwei")
@SpringBootApplication
public class EngineApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EngineApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EngineApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Successful start!!");
        LOG.info("Address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
