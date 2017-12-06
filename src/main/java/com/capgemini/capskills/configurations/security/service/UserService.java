package com.capgemini.capskills.configurations.security.service;

import com.capgemini.capskills.configurations.security.models.SecurityUser;

public interface UserService {
	
    void save(SecurityUser user);

    SecurityUser findByLogin(String login);
}
