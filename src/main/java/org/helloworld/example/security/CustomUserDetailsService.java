package org.helloworld.example.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService
        implements UserDetailsService {

    private Log log = LogFactory.getLog(getClass());

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Calling: loadUserByUsername");
        return new User("username", "password", null);
    }

}
