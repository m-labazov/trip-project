package ua.home.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import ua.home.trip.repository.UserRepository;

@EnableSocial
@Configuration
public class SocialConfig implements SocialConfigurer {

    // @Autowired
    // private IUserService userService;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        // cfConfig.addConnectionFactory(new TwitterConnectionFactory(
        // env.getProperty("twitter.consumer.key"),
        // env.getProperty("twitter.consumer.secret")
        // ));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("facebook.app.id"), env
                .getProperty("facebook.app.secret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new UserRepository();
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

}
