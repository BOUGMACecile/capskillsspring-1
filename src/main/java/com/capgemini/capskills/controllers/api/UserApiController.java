package com.capgemini.capskills.controllers.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.User;

/**
 * Implements the following api requests
 * Get all the users: 						GET URL/users/
 * Get a specific user with his id:			GET URL/users/{id}
 * Delete a specific user with his id:		DELETE URL/users/{id}
 * Add a user writing all the attributes:	POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:		PUT 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:					PUT 'URL/users/{userId}/{skillId}'
 * 
 * @author quentin.prigent
 *
 */


@RestController
@RequestMapping("/users")
public class UserApiController {

	@Autowired
	private IBaseManager<User> manager;
	
	@Autowired
	private IBaseManager<Skill> managerSkill;
	
	/**
	 * Method get all users
	 * @return
	 */
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getAll() {
        return this.manager.getAll();
    }
	
    /**
     * Method to get a user with a specific id
     * @param id
     * @param response
     * @return
     */
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User get(@PathVariable Integer id, HttpServletResponse response) {
        User entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return entity;
    }
	
    /**
     * Delete a specific user with its id
     * @param id
     * @return
     */
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public User delete(@PathVariable Integer id) {
        User type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }
    
    /**
     * Add an user, all the attributes are needed
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return
     */
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public User create(@RequestParam String firstname, String lastname, String email, String password, Boolean referent) {
        User entity = new User();

        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setReferent(referent);

        this.manager.create(entity);

        return entity;
    }

    /**
     * Update an user, all the attributes are needed
     * @param response
     * @param id
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return
     */
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public User update(HttpServletResponse response, @PathVariable int id, @RequestParam String firstname, String lastname, String email, String password, Boolean referent) {
    	
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
        } if(referent != null && !referent.equals(entity.getReferent())) {
        	entity.setReferent(referent);
        } else {
            response.setStatus(418);
        } 
    	
    	this.manager.update(entity);
    	
    	return null;
    }
    
    /**
     * Bind a skill to a user
     * @param userId
     * @param skillId
     * @return
     */
    @RequestMapping(value="/{userId}/{skillId}", method=RequestMethod.PUT)
    public List<Skill> addSkill(@PathVariable Integer userId, @PathVariable Integer skillId){
    	User user = this.manager.getById(userId);
    	Skill skill = this.managerSkill.getById(skillId);
    	List<Skill> skills = user.getSkills(); 
    	skills.add(skill);
    	this.manager.update(user);
    	return skills;
    }
    
    @RequestMapping(value="/display-skills/{userId}", method=RequestMethod.GET)
    public List<Skill> displaySkill(@PathVariable Integer userId) {
    	User user = this.manager.getById(userId);
    	List<Skill> skills = user.getSkills();
    	return skills;
    }
}
