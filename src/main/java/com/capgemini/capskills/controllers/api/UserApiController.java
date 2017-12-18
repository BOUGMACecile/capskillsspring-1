package com.capgemini.capskills.controllers.api;

import java.util.ArrayList;

import java.util.Iterator;

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


import com.capgemini.capskills.managers.UserManager;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.User;

/**
 * Implements the following api requests
<<<<<<< HEAD
 * Get all the users: 						GET URL/users/
 * Get a specific user with his id:			GET URL/users/{id}
 * Delete a specific user with his id:		DELETE URL/users/{id}
 * Add a user writing all the attributes:	POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:		PUT 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:					PUT 'URL/users/{userId}/{skillId}'
=======
 * Get all the users: 							GET URL/users/
 * Get a specific user with his id:				GET URL/users/{id}
 * Display all this skills of a specific user:	GET URL/users/display-skills/{userId}
 * Delete a specific user with his id:			DELETE URL/users/{id}
 * Add a user writing all the attributes:		POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:			POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:						POST 'URL/users/{userId}/{skillId}'
>>>>>>> cecile
 * 
 * @author quentin.prigent
 *
 */


@RestController
@RequestMapping("/users")
public class UserApiController {

	@Autowired

	private UserManager manager;

	
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
    

    @RequestMapping(value="/{id}", method=RequestMethod.POST)
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


    @RequestMapping(value="/{userId}/{skillId}", method=RequestMethod.POST)
    public List<Skill> addSkill(@PathVariable Integer userId, @PathVariable Integer skillId){
    	User user = this.manager.getById(userId);
    	Skill skill = this.managerSkill.getById(skillId);
    	List<Skill> skills = user.getSkills(); 
    	skills.add(skill);
    	this.manager.update(user);
    	return skills;
    }
    
    /**
<<<<<<< HEAD
     * This method binds a skill to a user
     * @param response
     * @param skillTypeId
     * @param skillName
     * @return
     */
//    @RequestMapping(value="/{userId}/skillname", method=RequestMethod.POST)
//    public User createSkill(HttpServletResponse response, @PathVariable int userId, @RequestParam(value = "skillname") String skillName) {
//    	User entity = this.manager.getById(userId);
//
//        if (entity == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } else if(skillName != null ) {
//            entity.addSkill(new Skill(skillName));
//            this.manager.create(entity);
//           
//        } else {
//            response.setStatus(418);
//        }
//        return entity;
//    }

//    @RequestMapping(value="/{userId}/{skillId}", method=RequestMethod.POST)
//    public void addSkill(HttpServletResponse response, @PathVariable int userId, int skillId) {
//    	User entity = this.manager.getById(userId);
//    	Skill skill = this.managerSkill.getById(skillId);
//    	
//    	if (entity == null || skill == null) {
//    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    	} else if (entity != null && skill != null) {
//    		entity.addSkill(skill);
//    	} else {
//    		response.setStatus(418);
//    	}
//    }
  
/**************************************************************************************************/    
    
    /**
     * This method deletes a skill from an user
     * @param skillTypeId
     * @param skillId
     * @return
     */
//    @RequestMapping(value = "/{userId}/skills/{skillId}", method = RequestMethod.DELETE)
//    public User deleteSkill(@PathVariable Integer userId, @PathVariable Integer skillId) {
//        User type = this.manager.getById(userId);
//      
//        if (type != null) {
//        	//on parcoure la liste des skillType afin de retrouver le skill que l'on doit supprimer
//        	Iterator<Skill> iterator = type.getSkills().iterator();
//        	//on intialise un bouleen find à false, sa valeur sera à true trouve si l'on trouve lélément à supprimer
//        	boolean find = false;
//        	Skill skill = null;
//    		while (iterator.hasNext() && find==false) {
//    			skill=iterator.next();
////    			if(skill.getId() == skillId) {
//    			if(skill.getId().equals(skillId)) {
//    				find=true;
//    			}
//    		}
//    		if(find == true)
//    			{
//    			//suppression du skill dans l'objet de persistance
//    			type.removeSkill(skill);  		
//    			//MAJ de la BDD
//    			this.manager.update(type);
//    			}
//        }
//        
//        return type;
//    }
//
//    /**
//     * Display the skills of an user
//     * @param response
//     * @param skillTypeId
//     * @return
//     */
//  	@RequestMapping(value="/{userId}/skills", method=RequestMethod.GET)
//  	public List<Skill> showUserSkills(HttpServletResponse response, @PathVariable int userId) {
//  		User entity = this.manager.getById(userId);
//  		List<Skill> skills = new ArrayList<Skill>();
//  		if (entity== null) {
//  			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//  		} else {
//  			skills = entity.getSkills();
//  		}
//  		return skills;
//  	}
    
}

     * Display all the skills of a user
     * @param userId
     * @return
     */
    @RequestMapping(value="/display-skills/{userId}", method=RequestMethod.GET)
    public List<Skill> displaySkill(@PathVariable Integer userId) {
    	User user = this.manager.getById(userId);
    	List<Skill> skills = user.getSkills();
    	return skills;
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
}

