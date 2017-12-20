package com.capgemini.capskills.controllers.api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.base.UserManager;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.SkillType;
import com.capgemini.capskills.models.User;

/**
 * Implements the following api requests

 * Get all the users: 						GET URL/users/
 * Get a specific user with his id:			GET URL/users/{id}
 * Delete a specific user with his id:		DELETE URL/users/{id}
 * Add a user writing all the attributes:	POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:		PUT 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:					PUT 'URL/users/{userId}/{skillId}'

 * Get all the users: 							GET URL/users/
 * Get a specific user with his id:				GET URL/users/{id}
 * Display all this skills of a specific user:	GET URL/users/display-skills/{userId}
 * Delete a specific user with his id:			DELETE URL/users/{id}
 * Add a user writing all the attributes:		POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:			POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:						POST 'URL/users/{userId}/{skillId}'

 * 
 * @author quentin.prigent
 *
 */


@RestController
@RequestMapping("/users")
public class UserApiController {

	@Autowired
	private UserManager manager;
	
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
    public User create(@RequestParam String firstname, String lastname, String email, String password, List<Integer> collaborators, Boolean referent) {
        User entity = new User();

        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setReferent(referent);
        
        manageCollaborators(collaborators, entity);

        this.manager.create(entity);

        return entity;
    }

    /** The process to update collaborator in new/known user. */
	private void manageCollaborators(List<Integer> collaborators, User entity) {
		List<User> serfs = new LinkedList<>();
		
		if (collaborators != null) {
	        collaborators.forEach(id -> serfs.add(this.manager.getById(id)));

	        entity.setCollaborators(serfs);
        }
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
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public User update(HttpServletResponse response, @PathVariable int id, @RequestParam String firstname, String lastname, String email, String password, List<Integer> collaborators, Boolean referent) {
    	
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
        
        this.manageCollaborators(collaborators, entity);
    	
    	this.manager.update(entity);
    	
    	return null;
    }

    
    @GetMapping("/login")
    public User connectionAction(HttpServletResponse response, @RequestParam String email, @RequestParam String password) {
    	User user = this.manager.getByEmail(email);
    	
    	// TODO Add security.
    	if (user == null || user.getPassword() == null || !user.getPassword().equals(password)) {
    		response.setStatus(500);
    		return null;
    	}
    	
    	return user;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/fill", method=RequestMethod.POST)
    public List<User> fill() {
    	
    	List<User> users = new ArrayList<User>();
    	
    	users.add(new User("collab1", "collab1", "collab1@capgemini.com", "collab1234", false));
    	users.add(new User("collab2", "collab2", "collab2@capgemini.com", "collab1234", false));
    	users.add(new User("collab3", "collab3", "collab3@capgemini.com", "collab1234", true));
    	
    	for (User user:users) {
            this.manager.create(user);
    	}   
        return this.getAll();
    }
    
    
    
    /*TEST WITH JULES*/
    
  
}


