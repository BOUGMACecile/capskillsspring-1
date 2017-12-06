package com.capgemini.capskills.controllers.api;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/skill-types")
public class SkillTypeApiController {

    @Autowired
    private IBaseManager<SkillType> manager;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<SkillType> getAll() {
        return this.manager.getAll();
    }
         
    // Récupérer 1 skill type dans la BDD
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public SkillType get(@PathVariable Integer id, HttpServletResponse response) throws JsonProcessingException {
        SkillType entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        System.out.println(new ObjectMapper().writeValueAsString(entity.getSkills()));
        return entity;
    }

    
    // Supprimer 1 skill type
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public SkillType delete(@PathVariable Integer id) {
        SkillType type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public SkillType create(@RequestParam(value = "skillTypeName") String skillTypeName) {
        SkillType entity = new SkillType();
        entity.setSkillTypeName(skillTypeName);
        this.manager.create(entity);

        return entity;
    }
    
    // Mise à jour d'1 skill type
    @RequestMapping(value="/{skillTypeId}", method=RequestMethod.PUT)
    public SkillType update(HttpServletResponse response, @PathVariable int skillTypeId, @RequestParam String skillTypeName) {
        SkillType entity = this.manager.getById(skillTypeId);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(skillTypeName != null && !skillTypeName.equals(entity.getSkillTypeName())) {
            entity.setSkillTypeName(skillTypeName);
            this.manager.update(entity);
        } else {
            response.setStatus(418);
        }

        return entity;
    }

    
    //Créer 1 Skill dans la BDD en spécifiant le Skill type auquel il appartient
    @RequestMapping(value="/{skillTypeId}/skill", method=RequestMethod.POST)
    public SkillType createSkill (HttpServletResponse response, @PathVariable int skillTypeId, @RequestParam(value = "skillName") String skillName) {
    	SkillType entity = this.manager.getById(skillTypeId);

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
    
    @RequestMapping(value="/{skillTypeId}/skills/{skillId}", method=RequestMethod.DELETE)
    public SkillType deleteSkill(@PathVariable Integer skillTypeId,@PathVariable Integer skillId) {
        SkillType type = this.manager.getById(skillTypeId);
       
        if (type != null) {
        	//on parcoure la liste des skillType afin de retrouver le skill que l'on doit supprimer
        	Iterator<Skill> iterator = type.getSkills().iterator();
        	//on intialise un bouleen find à false, sa valeur sera à trouve si l'on trouve lélément à supprimer
        	boolean find=false;
        	Skill skill=null;
    		while (iterator.hasNext()&& find==false) {
    			skill=iterator.next();
    			if(skill.getId()==skillId)
    				find=true;
    		}
    		if(find==true)
    			{
    			//suppression du skill dans l'objet de persistance
    			type.removeSkill(skill);  		
    			//MAJ de la BD
    			this.manager.update(type);
    			}
        }

        return type;
    }
    
  // Insert tous les 6 ST automatiquement dans la BDD
    @RequestMapping(value="/fill", method=RequestMethod.POST)
    public List<SkillType> fill() {
        for (String skillTypeName : Arrays.asList("Language", "Network", "Database", "Management","Tools","Soft")) {
            SkillType type = new SkillType(skillTypeName);
            this.manager.create(type);
        }

        return this.getAll();
    }
    
  //afficher la liste des skills d'un skillType
	@RequestMapping(value="/{skillTypeId}/skills", method=RequestMethod.GET)
	public List<Skill> showProjectSkills (HttpServletResponse response, @PathVariable int skillTypeId) {
		SkillType entity = this.manager.getById(skillTypeId);
		List<Skill> skills= new ArrayList<Skill>();
		if (entity== null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			skills=entity.getSkills();
		}

		return skills;
	}
}
