package org.helloworld.example.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;


public class CustomAuthenticationProvider
        implements AuthenticationProvider {

    private Log log = LogFactory.getLog(getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Calling: authenticate");
        String principal = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        log.info("authentication => Username: " + authentication.getPrincipal() + " Password: " + authentication.getCredentials());

        User myUser = (User) authentication.getPrincipal();
        log.info("authentication.getPrincipal() => Username: " + myUser.getUsername() + " Password: " + myUser.getPassword());

        if(myUser.getUsername().equals(myUser.getPassword())) {
            return authentication;
//            return new UsernamePasswordAuthenticationToken(
//                    myUser.getUsername(),
//                    myUser.getPassword(),
//                    authentication.getAuthorities());
        } else {
            throw new BadCredentialsException("Username/Password does not match for " + principal);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
