package com.capgemini.capskills.controllers.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.capgemini.capskills.managers.interfaces.base.IBaseManager;
import com.capgemini.capskills.models.base.BaseEntity;

public abstract class BaseController <T extends BaseEntity> {

	public final static String SHOW_ACTION = "show";
	public final static String CREATE_ACTION = "create";
	public final static String UPDATE_ACTION = "update";
	public final static String DELETE_ACTION = "delete";
	public final static String LIST_ACTION = "list";

	public final static String REDIRECT = "redirect:";

	public final static String PATH = "/";
	public final static String PATH_SHOW_FILE = PATH + SHOW_ACTION;
	public final static String PATH_CREATE_FILE = PATH + CREATE_ACTION;
	public final static String PATH_UPDATE_FILE = PATH + UPDATE_ACTION;
	public final static String PATH_DELETE_FILE = PATH + DELETE_ACTION;
	public final static String PATH_LIST_FILE = PATH + LIST_ACTION;

	public final static String ROUTE_SHOW = "{id}/" + SHOW_ACTION;
	public final static String ROUTE_CREATE = CREATE_ACTION;
	public final static String ROUTE_UPDATE = "{id}/" + UPDATE_ACTION;
	public final static String ROUTE_DELETE = "{id}/" + DELETE_ACTION;
	public final static String ROUTE_LIST = LIST_ACTION;

	@Autowired
	private IBaseManager<T> baseCrud;

	private Class<T> clazz;

	/**
	 * @return the clazz
	 */
	protected Class<T> getClazz() {
		return clazz;
	}

	protected BaseController(Class<T> clazz){
		this.clazz = clazz;
	}

	public T insertItem(@ModelAttribute T item){
		baseCrud.create(item);
		return item;
	}

	public String updateItem(@ModelAttribute T item){
		try {
			baseCrud.update(item);
		} catch (Exception e) {
			return "Update failed";
		}
		return "Update OK";
	}

	public String deleteItem(@ModelAttribute T item){
		try {
			baseCrud.delete(item);
		} catch (Exception e) {
			return "Delete failed";
		}
		return "Delete OK";
	}

	public T getItem(Integer id){
		T item = null;
		item = baseCrud.getById(id);
		return item;
	}

	public List<T> getItems(){
		List<T> items = null;
		items = (List<T>) baseCrud.getAll();
		return items;
	}
}
