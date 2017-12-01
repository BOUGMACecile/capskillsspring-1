package com.capgemini.capskills.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.capgemini.capskills.dao.interfaces.base.IBaseDAO;
import com.capgemini.capskills.models.Project;

@Repository
public interface IProjectDAO extends IBaseDAO<Project> {

}
