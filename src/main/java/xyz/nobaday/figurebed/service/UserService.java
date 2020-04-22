package xyz.nobaday.figurebed.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.nobaday.figurebed.dao.UserMapper;
import xyz.nobaday.figurebed.entity.User;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(FigureService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return null;
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .authorities(user.getPermission())
                .build();
        return userDetails;
    }
}
