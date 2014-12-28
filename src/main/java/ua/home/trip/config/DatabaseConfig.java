package ua.home.trip.config;

import com.mongodb.MongoClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

import javax.annotation.Resource;

@Configuration
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Bean
    public MongoTemplate createMongoTemplate() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(env.getProperty("mongo.host"), Integer.parseInt(env
                .getProperty("mongo.port")));
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient,
                env.getProperty("mongo.db.name"));
        return new MongoTemplate(simpleMongoDbFactory);
    }

}
