package com.balaji.springappsecurityjwt.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
        return new User("foo","foo", new ArrayList<>()) ;
    }
}
