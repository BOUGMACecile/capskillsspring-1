package com.capgemini.capskills.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.Project;
import com.capgemini.capskills.models.Skill;
import com.capgemini.capskills.models.SkillType;

@RestController
@RequestMapping("/projects")
public class ProjectApiController {

	@Autowired
	private IBaseManager<Project> manager;
	@Autowired
	private IBaseManager<Skill> skillManager;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Project> getAll() {
		return this.manager.getAll();
	}

	//private  AtomicInteger counter = new AtomicInteger();


//Afficher les informations concernant un projet
	@RequestMapping(value="/{id}", method=RequestMethod.GET)// on définit la structure de l'url
	public Project get(@PathVariable Integer id, HttpServletResponse response) {
		Project entity = this.manager.getById(id);

		if (entity == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		return entity;
	}
// Supprimer un projet
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Project delete(@PathVariable Integer id) {
		Project type = this.manager.getById(id);

		if (type != null) {
			this.manager.delete(type);
		}

		return type;
	}
	// ajouter 1 project
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Project create(@RequestParam(value = "projectName") String projectName,@RequestParam(value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,@RequestParam(value = "comment") String comment,@RequestParam(value = "userId") double userId) {
		Project entity = new Project();

		entity.setProjectName(projectName);
		entity.setBeginDate(beginDate);
		entity.setEndDate(endDate);
		entity.setComment(comment);
		entity.setUserId(userId);

		this.manager.create(entity);

		return entity;
	}
	/*   @RequestMapping(value="/", method=RequestMethod.POST)
    public Project create(@RequestParam(value = "projectName") String projectName,@RequestParam(value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,@RequestParam(value = "comment") String comment,@RequestParam(value = "userId") double userId,@RequestParam(value = "skill") List <Skill> skill) {
        Project entity = new Project();

       entity.setProjectName(projectName);
       entity.setBeginDate(beginDate);
       entity.setEndDate(endDate);
       entity.setComment(comment);
       entity.setUserId(userId);
      // entity.se..setskill(skill);
        this.manager.create(entity);

        return entity;
    }
	 */

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Project update(HttpServletResponse response, @PathVariable int id, @RequestParam(value = "projectName") String projectName,@RequestParam(value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,@RequestParam(value = "comment") String comment,@RequestParam(value = "userId") double userId) {
		Project entity = this.manager.getById(id);

		if (entity == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else if(projectName != null && !projectName.equals(entity.getProjectName())) {
			entity.setProjectName(projectName);
			entity.setBeginDate(beginDate);
			entity.setEndDate(endDate);
			entity.setComment(comment);
			entity.setUserId(userId);

			this.manager.update(entity);
		} else {
			response.setStatus(418);
		}

		return entity;
	}
	/*  @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Project update(HttpServletResponse response, @PathVariable int id, @RequestParam(value = "projectName") String projectName,@RequestParam(value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,@RequestParam(value = "comment") String comment,@RequestParam(value = "userId") double userId, @RequestParam(value = "skill") List<Skill> skill) {
        Project entity = this.manager.getById(id);

        if (entity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(projectName != null && !projectName.equals(entity.getProjectName())) {
            entity.setProjectName(projectName);
            entity.setBeginDate(beginDate);
            entity.setEndDate(endDate);
            entity.setComment(comment);
            entity.setUserId(userId);
           // entity.setskill(skill);
            this.manager.update(entity);
        } else {
            response.setStatus(418);
        }

        return entity;
    }
	 */

	@RequestMapping(value="/fill", method=RequestMethod.POST)
	public List<Project> fill() {
		/* for (String ProjectName : Arrays.asList("Soft Skill", "Technical Skill", "Management Skill", "Mega", "Ultra")) {
            Project type = new Project(ProjectName);
            this.manager.create(type);
        }
		 */
		return this.getAll();
	}

	//Ajouter/affecter  1 Skill à 1 project
	@RequestMapping(value="/{projectId}/skill", method=RequestMethod.POST)
	public Project associateSkillToProject (HttpServletResponse response, @PathVariable int projectId, @RequestParam(value = "skillId") Integer skillId) {
		Project entity = this.manager.getById(projectId);
		Skill skillEntity = this.skillManager.getById(skillId);
		if (skillEntity == null ||entity== null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else if(skillId != null ) {
			entity.getSkills().add(skillEntity);// depuis la persistance, on lui dit dit d'ajouter le skill dans le project.
			this.manager.create(entity);

		} else {
			response.setStatus(418);
		}

		return entity;
	}
	
	//afficher la liste des skills d'un projet
		@RequestMapping(value="/{projectId}/skills", method=RequestMethod.GET)
		public List<Skill> showProjectSkills (HttpServletResponse response, @PathVariable int projectId) {
			Project entity = this.manager.getById(projectId);
			List<Skill> skills= new ArrayList<Skill>();
			if (entity== null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				skills=entity.getSkills();
			}

			return skills;
		}
		
	
		//supprimer  1 Skill d'un project
		@RequestMapping(value="/{projectId}/skills/{skillId}", method=RequestMethod.DELETE)
		public Project deleteSkillToProject (HttpServletResponse response, @PathVariable int projectId, @PathVariable int  skillId) {
			Project entity = this.manager.getById(projectId);
			
			if (entity== null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else if(skillId >0 ) {
				//on parcoure la liste des skillType afin de retrouver le skill que l'on doit supprimer
	        	Iterator<Skill> iterator = entity.getSkills().iterator();
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
	    			entity.getSkills().remove(skill);  	
	    			//skill.setSkillType(null);
	    			//MAJ de la BD
	    			this.manager.update(entity);
	    			}
			
			} else {
				response.setStatus(418);
			}

			return entity;
		}

}
