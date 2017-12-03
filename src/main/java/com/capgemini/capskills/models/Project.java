package com.capgemini.capskills.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.capskills.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
    name = "project",
    uniqueConstraints=@UniqueConstraint(columnNames="id")
)
public class Project extends BaseEntity {
	@Column(name="project_name")
	private String projectName;
	@Column(name="begin_date")
	private Date beginDate;
	@Column(name="end_date")
	private Date endDate;
	@Column(name="comment")
    private String comment;
	@Column(name="user_id")
	private double userId;
	
	
	@ManyToMany
	  @JoinTable(
	      name="skillproject",
	      joinColumns=@JoinColumn(name="project_id", referencedColumnName="id"),
	      inverseJoinColumns=@JoinColumn(name="skill_Id", referencedColumnName="id"))
	  private List<Skill> skills=new ArrayList<Skill>();
	 
	
	public Project()
	{
		
	}
	public Project(String projectName, Date beginDate, Date endDate, String comment, double userId) {
		super();
		this.projectName = projectName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.comment = comment;
		this.userId = userId;
	
	}
	
	public Project(String projectName, Date beginDate, Date endDate, String comment, double userId,
			List<Skill> skills) {
		super();
		this.projectName = projectName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.comment = comment;
		this.userId = userId;
		this.skills = skills;
	}
	public Project(Integer id,String projectName, Date beginDate, Date endDate, String comment, double userId) {
		super(id);
		
		this.projectName = projectName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.comment = comment;
		this.userId = userId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getUserId() {
		return userId;
	}
	public void setUserId(double userId) {
		this.userId = userId;
	}
	
	@JsonIgnore
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", comment=" + comment + ", userId=" + userId + ", skills=" + skills + "]";
	}
	
	
	
}
