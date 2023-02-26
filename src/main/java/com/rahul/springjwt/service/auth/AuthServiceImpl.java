package com.rahul.springjwt.service.auth;

import com.rahul.springjwt.model.dao.RoleDao;
import com.rahul.springjwt.model.dao.UserDao;
import com.rahul.springjwt.repository.RoleRepository;
import com.rahul.springjwt.repository.UserRepository;
import com.rahul.springjwt.security.service.JWTUtils;
import com.rahul.springjwt.security.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "AuthService")
@Component
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String login(String userName, String password) {
        logger.info("Logging user");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJWToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return jwt;
    }

    @Override
    public void signUp(String userName, String password, List<String> roles){
        UserDao userDao = new UserDao();
        userDao.setUsername(userName);
        userDao.setPassword(passwordEncoder.encode(password));
        userDao = userRepository.save(userDao);

        List<RoleDao> roleDaoList = new ArrayList<>();
        for (String role : roles){
            RoleDao roleDao = new RoleDao();
            roleDao.setRole(role);
            roleDao.setUserDao(userDao);
            roleDaoList.add(roleDao);
        }

        roleRepository.saveAll(roleDaoList);


    }
}
