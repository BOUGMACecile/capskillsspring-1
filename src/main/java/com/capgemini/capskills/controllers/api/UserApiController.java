package com.capgemini.capskills.controllers.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.User;

@RestController
@RequestMapping("/users")
public class UserApiController {

	@Autowired
	private IBaseManager<User> manager;
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getAll() {
        return this.manager.getAll();
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User get(@PathVariable Integer id, HttpServletResponse response) {
        User entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return entity;
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public User delete(@PathVariable Integer id) {
        User type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public User create(@RequestParam String firstname, String lastname, String email, String password) {
        User entity = new User();

        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        entity.setEmail(email);
        entity.setPassword(password);

        this.manager.create(entity);

        return entity;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public User update(HttpServletResponse response, @PathVariable int id, @RequestParam String firstname, String lastname, String email, String password) {
    	
    	User entity = this.manager.getById(id);
    	
        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } if(firstname != null && !firstname.equals(entity.getFirstname())) {
            entity.setFirstname(firstname);
        } if(lastname != null && !lastname.equals(entity.getLastname())) {
        	entity.setLastname(lastname);
        } if(email != null && !email.equals(entity.getEmail())) {
        	entity.setEmail(email);
        } if(password != null && !password.equals(entity.getPassword())) {
        	entity.setPassword(password);
        } else {
            response.setStatus(418);
        }
    	
    	this.manager.update(entity);
    	
    	return null;
    }
    
}
