package com.capgemini.capskills.configurations.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.capskills.configurations.security.dao.ISecurityRoleCrudRepository;
import com.capgemini.capskills.configurations.security.dao.ISecurityUserCrudRepository;
import com.capgemini.capskills.configurations.security.models.SecurityRole;
import com.capgemini.capskills.configurations.security.models.SecurityUser;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ISecurityUserCrudRepository userRepository;
    @Autowired
    private ISecurityRoleCrudRepository roleRepository;

    @Override
    public void save(SecurityUser user) {
        user.setPassword(user.getPassword());
        user.setRoles((Set<SecurityRole>)roleRepository.findAll());
        userRepository.save(user);
    }

    @Override
    public SecurityUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
