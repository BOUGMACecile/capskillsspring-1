package com.capgemini.capskills.controllers.api;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.GradingManager;
import com.capgemini.capskills.managers.base.UserManager;
import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Grading;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.User;

/**
 * Implements the following api requests
<<<<<<< HEAD
 * Get all grading systems: 				GET URL/grading/
 * Get a grading system with a specific id:	GET URL/grading/{gradingId}
=======
 * Get all the grades: 						GET URL/grading/
 * 
 * 
>>>>>>> 29c0f3ed7a0062e041d4356a0a66a11eccaa4eaa
 * Add a grading system:					POST URL/grading/?collaboratorgrade=collaboratorgrade&targetgrade=targetgrade&actualgrade=actualgrade
 * Delete a specific user with his id:		DELETE URL/users/{id}
 * 
 * Add a user writing all the attributes:	POST 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Update an user with all attributes:		PUT 'URL/users/?firstname=firstname&lastname=lastname&email=email&password=password&referent=referent'
 * Bind a skill to a user:					PUT 'URL/users/{userId}/{skillId}'
 * 
 * @author quentin.prigent
 *
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/grading")
public class GradingApiController {

	@Autowired
	private IBaseManager<Grading> manager;
	
	@Autowired
	private UserManager managerUser;
	
	@Autowired
	private IBaseManager<Skill> managerSKill;
	
	
	@Autowired
	private GradingManager managerGranding;
	/**
	 * Method get all grades
	 * @return
	 */
	@CrossOrigin(origins = "*")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Grading> getAll() {
        return this.manager.getAll();
    }
	
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
    public List<Grading> getUserGradings(HttpServletResponse response, @PathVariable Integer userId) {
		String query="SELECT g FROM Grading g WHERE g.user.id="+userId;
        return (List<Grading> )this.manager.select(query);
    }
    /**
     * Method to get a grading system with a specific id
     * @param id
     * @param response
     * @return
     */
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Grading get(@PathVariable Integer id, HttpServletResponse response) {
        Grading entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return entity;
    }
    
    /**
     * Delete a specific grading system with its id
     * @param id
     * @return
     */
	
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Grading delete(@PathVariable Integer id) {
        Grading type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }
    
    /**
     * Add a grading system
     * @param collaboratorgrade
     * @param targetgrade
     * @param actualgrade
     * @return
     */
    

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Grading create(@RequestParam Integer collaboratorgrade, @RequestParam Integer targetgrade, @RequestParam Integer actualgrade) {

    	Grading entity = new Grading();

        entity.setCollaboratorgrade(collaboratorgrade);
        entity.setActualgrade(actualgrade);
        entity.setTargetgrade(targetgrade);

        this.manager.create(entity);

        return entity;
    }
    

    /**
     * Update a collaborator grade of a grading system
     * @param response
     * @param id
     * @param collaboratorgrade
     * @return
     */


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Grading updateCollaboratorGrade(HttpServletResponse response, @PathVariable Integer id, @RequestParam Integer collaboratorgrade, @RequestParam Integer actualgrade, @RequestParam Integer targetgrade) {
    	Grading entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } if(collaboratorgrade != null && !collaboratorgrade.equals(entity.getCollaboratorgrade())) {
            entity.setCollaboratorgrade(collaboratorgrade);
        } if(actualgrade != null && !actualgrade.equals(entity.getActualgrade())) {
        	entity.setActualgrade(actualgrade);
        } if(targetgrade != null && !targetgrade.equals(entity.getTargetgrade())) {
        	entity.setTargetgrade(targetgrade);
        } else {
            response.setStatus(418);
        }

        this.manager.update(entity);
        
        return entity;
    }

    /**
     * Bind a user to a grading system
     * @param gradingId
     * @param userId
     * @return
     */
    @RequestMapping(value="/adduser/{gradingId}/{userId}", method=RequestMethod.PUT)
    public Grading addUser(@PathVariable Integer gradingId, @PathVariable Integer userId) {
    	Grading grading = this.manager.getById(gradingId);
    	User user = this.managerUser.getById(userId);
    	grading.setUser(user);  
    	this.manager.update(grading);
    	return grading;
    }
    
    /**
     * Bind a skill to a grading system
     * @param gradingId
     * @param userId
     * @return
     */
    @RequestMapping(value="/addskill/{gradingId}/{skillId}", method=RequestMethod.PUT)
    public Grading addSkill(@PathVariable Integer gradingId, @PathVariable Integer skillId) {
    	Grading grading = this.manager.getById(gradingId);
    	Skill skill = this.managerSKill.getById(skillId);
    	grading.setSkill(skill);
    	this.manager.update(grading);
    	return grading;
    }
   

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/addGrading", method=RequestMethod.POST)
    public Grading createGrading(@RequestParam Integer actualgrade, @RequestParam Integer userId, @RequestParam Integer skillId) {
    	Grading entity = new Grading();
    	Skill skill = this.managerSKill.getById(skillId);
    	User user = this.managerUser.getById(userId);
        entity.setCollaboratorgrade(0);
        entity.setTargetgrade(0);

        entity.setActualgrade(actualgrade);
        entity.setSkill(skill);
        entity.setUser(user);

        this.manager.create(entity);

        return entity;
    }

}

