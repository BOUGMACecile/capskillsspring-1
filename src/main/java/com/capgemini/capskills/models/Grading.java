package com.capgemini.capskills.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.capgemini.capskills.models.base.BaseEntity;

@Entity
@Table(name = "grading")
public class Grading extends BaseEntity {
	
	private Integer collaboratorgrade;
	private Integer targetgrade;
	private Integer initialgrade;
	
	

		//Specific Constructor
		public Grading(Integer collaboratorgrade) {
			super();
			this.collaboratorgrade = collaboratorgrade;
		}
		
		public Grading(Integer collaboratorgrade, Integer targetgrade) {
			super();
			this.collaboratorgrade = collaboratorgrade;
			this.targetgrade = targetgrade;
		}
		
		public Grading(Integer collaboratorgrade, Integer targetgrade, Integer initialgrade) {
			super();
			this.collaboratorgrade = collaboratorgrade;
			this.targetgrade = targetgrade;
			this.initialgrade = initialgrade;
		}
		
		//getter and setter
		
		public Integer getCollaboratorgrade() {
			return collaboratorgrade;
		}

		public void setCollaboratorgrade(Integer collaboratorgrade) {
			this.collaboratorgrade = collaboratorgrade;
		}

		public Integer getTargetgrade() {
			return targetgrade;
		}

		public void setTargetgrade(Integer targetgrade) {
			this.targetgrade = targetgrade;
		}

		public Integer getInitialgrade() {
			return initialgrade;
		}

		public void setInitialgrade(Integer initialgrade) {
			this.initialgrade = initialgrade;
		}

		//Default Constructor
		public Grading(){}




//		@Override
//		public String toString() {
//			return "Grading [collaboratorgrade=" + this.collaboratorgrade + ",targetgrade=" + this.targetgrade + ",initialgrade=" + this.initialgrade +"]";
//		}

	


}
