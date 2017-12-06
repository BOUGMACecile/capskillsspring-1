package com.capgemini.capskills.controllers.api;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
@RequestMapping("/skills")
public class SkillApiController {

    @Autowired
    private IBaseManager<Skill> manager;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Skill> getAll() {
        return this.manager.getAll();
    }
   
   
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Skill get(@PathVariable Integer id, HttpServletResponse response)  {
        Skill entity = this.manager.getById(id);
       
        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
       
        return entity;
    }

  /* @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Skill delete(@PathVariable Integer id) {
        Skill type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }

  
    

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Skill create(@RequestParam(value = "skillTypeId") Integer skillTypeId,@RequestParam(value = "name") String name) {
        Skill entity = new Skill(name);
       // entity.setSkillTypeId(skillTypeId);
        this.manager.create(entity);

        return entity;
    }*/
    
    @RequestMapping(value="/{skillId}", method=RequestMethod.PUT)
    public Skill update(HttpServletResponse response, @PathVariable int skillId, @RequestParam String skillName) {
        Skill entity = this.manager.getById(skillId);

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

    @RequestMapping(value="/fill", method=RequestMethod.POST)
    public List<Skill> fill() {
        for (String name : Arrays.asList("Langage", "Network", "Database", "Management","Tools","Soft")) {
            Skill type = new Skill(name);
            this.manager.create(type);
        }

        return this.getAll();
    }
}