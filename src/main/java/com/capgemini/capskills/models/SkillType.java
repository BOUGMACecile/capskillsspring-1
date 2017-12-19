package com.capgemini.capskills.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;

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

//	public void addSkill(Skill skill) {
//        skills.add(skill);
//        skill.setSkillType(this);
//    }
// 
//    public void removeSkill(Skill skill) {
//    	skills.remove(skill);
//    	skill.setSkillType(null);
//    }


	@Override
	public String toString() {
		return "SkillType [skillTypeName=" + skillTypeName + " ]";
	}
	
}
