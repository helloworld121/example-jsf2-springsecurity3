package org.helloworld.example.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hal
 * Date: 04.09.2011
 * Time: 18:08:36
 */
public class MyUserDetailsService
        implements UserDetailsService {

    private Log log = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        log.info("Calling loadUserByUsername with: " + username);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        String password = "aabcb987e4b425751e210413562e78f776de6285"; // test

        SaltedUser user = new SaltedUser(username, password, true, true, true, true, authorities, "salt"); 

        return user;
    }
}
