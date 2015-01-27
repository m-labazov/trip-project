package ua.home.trip.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/connect")
public class ConnectionController extends BaseController {

    private ConnectSupport connectSupport;
    private ConnectionFactoryLocator connectionFactoryLocator;

    public ConnectionController(ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
        this.connectionFactoryLocator = connectionFactoryLocator;
        connectSupport = new ConnectSupport();
    }

    @RequestMapping(value = "/{providerId}", method = RequestMethod.POST)
    public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId);
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        try {
			String callbackUrl = ((HttpServletRequest) request.getNativeRequest()).getRequestURL().toString()
					.replace("action/connect", "auth");
			connectSupport.setCallbackUrl(callbackUrl);
            return new RedirectView(connectSupport.buildOAuthUrl(connectionFactory, request, parameters));
        } catch (Exception e) {
            return new RedirectView();
        }
    }

}
