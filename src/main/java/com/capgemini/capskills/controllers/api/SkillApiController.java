package com.capgemini.capskills.controllers.api;
 
import java.util.ArrayList;
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

/**
 * Implements the following api requests
 * Get all the skills: 													GET URL/skills/
 * Get a skill with his specific id:									GET URL/skills/{id}
 * Get all the skills with a specific skill type:						GET URL/skills/display/{skilltypeid}
 * Create a skill binded to a specific skill type:				 	   	POST URL/skills/{skillTypeId}/?name=name
 * Delete a skill with its specific id:									DELETE URL/skills/{id}
 * Update a skill with its specific id:									PUT URL/skills/{id}/?name=name
 * 
 * 
 * @author quentin.prigent
 *
 */

@RestController
@RequestMapping("/skills")
public class SkillApiController {

    @Autowired
    private IBaseManager<Skill> manager;
    
    @Autowired
    private IBaseManager<SkillType> managerSkillType;
    
//    @Autowired
//    private IBaseManager<User> managerUser;
    

    /**
     * Get all skills
     * @return
     */
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Skill> getAll() {
        return this.manager.getAll();
    }   

    /**
     * Get a skill with a specific id
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Skill get(@PathVariable Integer id, HttpServletResponse response)  {
        Skill entity = this.manager.getById(id);      
        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return entity;
    }

    /**
     * Create a skill wit a specific skill type
     * @param skillTypeId
     * @param userId
     * @param name
     * @return
     */
    @RequestMapping(value="/{skillTypeId}/", method=RequestMethod.POST)
    public Skill create(@PathVariable Integer skillTypeId, @RequestParam(value = "name") String name, HttpServletResponse response) {
        Skill entity = new Skill(); 
        SkillType skillType = this.managerSkillType.getById(skillTypeId);
        
        entity.setName(name);
        entity.setSkillType(skillType);
        this.manager.create(entity);

        return entity;
    }
     
    /**
     * Delete a skill with a specific id
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Skill delete(@PathVariable Integer id) {
        Skill type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }
    
    /**
     * Update a skill with a specific id
     * @param response
     * @param id
     * @param skillName
     * @return
     */    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Skill update(HttpServletResponse response, @PathVariable int id, @RequestParam(value = "name") String skillName) {
        Skill entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(skillName != null && !skillName.equals(entity.getName())) {
            entity.setName(skillName);

            this.manager.update(entity);
        } else {
            response.setStatus(418);
        }

        return entity;
    }
  
    
    /**
     * Display all the skills associated to a specific skill type
     * @param skillTypeId
     * @return
     */
    @RequestMapping(value="/display/{skillTypeId}", method=RequestMethod.GET)
    public List<Skill> getAllById(@PathVariable Integer skillTypeId) {
    	List<Skill> unselectedSkills = this.manager.getAll();
    	List<Skill> skills = new ArrayList<Skill>();
    	SkillType skillType = this.managerSkillType.getById(skillTypeId);
    	for(Skill skill : unselectedSkills) {
    		if(skill.getSkillType() == skillType) {
    			skills.add(skill);
    		}
    	}
    	return skills;
    }

}
