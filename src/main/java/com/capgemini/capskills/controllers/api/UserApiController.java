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
import com.capgemini.capskills.models.SkillType;
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
    
    /**
     * This method binds a skill to a user
     * @param response
     * @param skillTypeId
     * @param skillName
     * @return
     */
    @RequestMapping(value="/{userId}/skill", method=RequestMethod.POST)
    public User createSkill(HttpServletResponse response, @PathVariable int userId, @RequestParam(value = "skillName") String skillName) {
    	User entity = this.manager.getById(userId);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(skillName != null ) {
            entity.addSkill(new Skill(skillName));
            this.manager.create(entity);
           
        } else {
            response.setStatus(418);
        }
        return entity;
    }

    /**
     * This method deletes a skill from an user
     * @param skillTypeId
     * @param skillId
     * @return
     */
    @RequestMapping(value = "/{userId}/skills/{skillId}", method = RequestMethod.DELETE)
    public User deleteSkill(@PathVariable Integer userId, @PathVariable Integer skillId) {
        User type = this.manager.getById(userId);
      
        if (type != null) {
        	//on parcoure la liste des skillType afin de retrouver le skill que l'on doit supprimer
        	Iterator<Skill> iterator = type.getSkills().iterator();
        	//on intialise un bouleen find à false, sa valeur sera à true trouve si l'on trouve lélément à supprimer
        	boolean find = false;
        	Skill skill = null;
    		while (iterator.hasNext() && find==false) {
    			skill=iterator.next();
//    			if(skill.getId() == skillId) {
    			if(skill.getId().equals(skillId)) {
    				find=true;
    			}
    		}
    		if(find == true)
    			{
    			//suppression du skill dans l'objet de persistance
    			type.removeSkill(skill);  		
    			//MAJ de la BDD
    			this.manager.update(type);
    			}
        }
        
        return type;
    }

    /**
     * Display the skills of an user
     * @param response
     * @param skillTypeId
     * @return
     */
  	@RequestMapping(value="/{userId}/skills", method=RequestMethod.GET)
  	public List<Skill> showUserSkills(HttpServletResponse response, @PathVariable int userId) {
  		User entity = this.manager.getById(userId);
  		List<Skill> skills = new ArrayList<Skill>();
  		if (entity== null) {
  			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
  		} else {
  			skills = entity.getSkills();
  		}
  		return skills;
  	}
}
