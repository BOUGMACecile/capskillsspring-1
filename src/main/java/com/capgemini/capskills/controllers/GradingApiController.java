package com.capgemini.capskills.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Grading;



@RestController
@RequestMapping("/grading")
public class GradingApiController {

	@Autowired
	private IBaseManager<Grading> manager;
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Grading> getAll() {
        return this.manager.getAll();
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Grading get(@PathVariable Integer id, HttpServletResponse response) {
        Grading entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return entity;
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Grading delete(@PathVariable Integer id) {
        Grading type = this.manager.getById(id);

        if (type != null) {
            this.manager.delete(type);
        }

        return type;
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public Grading create(@RequestParam Integer collaboratorgrade, @RequestParam Integer targetgrade, @RequestParam Integer initialgrade) {
    	Grading entity = new Grading();

        entity.setCollaboratorgrade(collaboratorgrade);
        entity.setTargetgrade(targetgrade);
        entity.setInitialgrade(initialgrade);

        this.manager.create(entity);

        return entity;
    }
    


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Grading update(HttpServletResponse response, @PathVariable Integer id, @RequestParam Integer collaboratorgrade) {
    	Grading entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(collaboratorgrade != null && !collaboratorgrade.equals(entity.getCollaboratorgrade())) {
            entity.setCollaboratorgrade(collaboratorgrade);

            this.manager.update(entity);
        } else {
            response.setStatus(418);
        }

        return entity;
    }

    @RequestMapping(value="/fill", method=RequestMethod.POST)
    public List<Grading> fill() {
        for (Integer collaboratorgrade : Arrays.asList(3, 4, 5, 6, 7)) {
        	Grading type = new Grading(collaboratorgrade);
            this.manager.create(type);
        }

        return this.getAll();
    }   

}

