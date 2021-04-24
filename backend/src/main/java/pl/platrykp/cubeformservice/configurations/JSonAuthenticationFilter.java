package pl.platrykp.cubeformservice.configurations;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class JSonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JSonAuthenticationFilter.class);

    public static final String USERNAME_PARAM_NAME = "username";
    public static final String PASSWORD_PARAM_NAME = "password";

    public JSonAuthenticationFilter(){
        super();
    }

    public JSonAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("IM DOING STUFF!");
        logger.info("IM DOING STUFF!");
        logger.info("IM DOING STUFF!");
        checkRequest(request);
        try {
            UsernameAndPassword usernameAndPassword = getAuthData(request.getReader());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    usernameAndPassword.username,
                    usernameAndPassword.password
            );

            setDetails(request, token);
            return getAuthenticationManager().authenticate(token);
        }
        catch (IOException er) {
            logger.error("Request body reader", er);
            throw new AuthenticationServiceException("Body read error");
        }
    }

    private void checkRequest(HttpServletRequest request) throws AuthenticationException {
        if(!request.getMethod().equals(HttpMethod.POST.name()))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if(!contentType.equals(MediaType.APPLICATION_JSON_VALUE))
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + contentType);
    }

    private UsernameAndPassword getAuthData(BufferedReader bodyReader) throws AuthenticationException {
        JSONObject jsonObject;
        try {
            String body = bodyReader.lines().collect(Collectors.joining("\n"));
            jsonObject = new JSONObject(body);
        }
        catch (JSONException er){
            throw new AuthenticationServiceException("Authentication body is not a valid JSON");
        }

        if(!jsonObject.has(USERNAME_PARAM_NAME))
            throw new AuthenticationServiceException("Authentication body has missing '" + USERNAME_PARAM_NAME + "' field");
        if(!jsonObject.has(PASSWORD_PARAM_NAME))
            throw new AuthenticationServiceException("Authentication body has missing '" + PASSWORD_PARAM_NAME + "' field");

        try {
            return new UsernameAndPassword(
                    jsonObject.getString(USERNAME_PARAM_NAME),
                    jsonObject.getString(PASSWORD_PARAM_NAME)
            );
        }
        catch (JSONException er){
            throw new AuthenticationServiceException("username or password is not a text");
        }
    }

    private static class UsernameAndPassword {
        public String username;
        public String password;

        public UsernameAndPassword(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
