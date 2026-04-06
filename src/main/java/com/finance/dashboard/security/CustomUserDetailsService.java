package com.finance.dashboard.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password("{noop}admin123")
                    .roles("ADMIN")
                    .build();
        }

        if (username.equals("analyst")) {
            return User.withUsername("analyst")
                    .password("{noop}analyst123")
                    .roles("ANALYST")
                    .build();
        }

        if (username.equals("viewer")) {
            return User.withUsername("viewer")
                    .password("{noop}viewer123")
                    .roles("VIEWER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}