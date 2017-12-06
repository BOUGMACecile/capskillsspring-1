package com.capgemini.capskills.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(
    name = "skilltype",
    uniqueConstraints=@UniqueConstraint(columnNames="id")
)
public class SkillType extends BaseEntity {

	/**
	 * Class Attributes
	 */
	
    @Column(name="skill_type_name")
    private String skillTypeName;

      
    @OneToMany(
    		fetch = FetchType.EAGER,
            mappedBy = "skillType", 
            cascade = CascadeType.ALL, 
            orphanRemoval = true
        )
    @JsonBackReference
    private List<Skill> skills = new ArrayList<Skill>();
    
    /**
     * Getters & Setters
     * @return
     */

	public String getSkillTypeName() {
		return skillTypeName;
	}

	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}
    
    @JsonBackReference
    public List<Skill> getSkills() {
		return skills;
	}
   
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	/**
	 * Constructors
	 */
	
	public SkillType() {
	}
        
	public SkillType(String skillTypeName) {
		super();	
		this.skillTypeName = skillTypeName;
	}

	public SkillType(Integer id, String skillTypeName) {
		super(id);
		this.skillTypeName = skillTypeName;
	}
	

	/**
	 * Methods
	 * @param skill
	 */

	public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setSkillType(this);
    }
 
    public void removeSkill(Skill skill) {
    	skills.remove(skill);
    	skill.setSkillType(null);
    }


	@Override
	public String toString() {
		return "SkillType [skillTypeName=" + skillTypeName + ", skills=" + skills.toString() + "]";
	}
	
}
