package com.capgemini.capskills.dao.interfaces.base;

import java.util.List;

public interface IBaseDAO<T> {
	
	public void create(T item);
	public void delete(T item);
	public List<T> getAll();
	public T getById(Integer id);
	public void update(T item);

}
