package de.medieninf.webanw.sakila;

import java.io.Serializable;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

public class FilmBackingBean implements Serializable {

	private static final long serialVersionUID = 4156913588347597255L;
	
	private List<Film> films = null;
	private List<Integer> pages;
	private String categoryId;
	private Category category;
	private int currentPage;
	private int startPage;
	private int endPage;
	private int filmId;
	private Film film;
	private int first;
	private int max;
	
	/*
	 * GETTER FILM ID
	 */
	
	public int getFilmId() { 
		return filmId; 
	}
	
	/*
	 * SETTER FILM ID
	 */

	public void setFilmId(int filmId) {
		this.filmId = filmId;		
		FacesContext fc = FacesContext.getCurrentInstance();
		SakilaBean sb = (SakilaBean) fc.getApplication().evaluateExpressionGet(fc, "#{sakila}", SakilaBean.class);
		film = sb.getFilm(filmId);
	}
	
	/*
	 * GETTER FILM
	 */

	public Film getFilm() {
		return film;
	}

	/**
	 * undocumented function
	 *
	 * @return List Actor  
	 */
	
	public List<Actor> getActorsFromFilm() {
		if (film == null) {
			return null;
		}
		return film.getActors();
	}
	
	/**
	 * get all films
	 *
	 * @return List Film  
	 */
	
	public List<Film> getFilms() {	
		
		if(films == null){
			FacesContext fc = FacesContext.getCurrentInstance();
			SakilaBean sb = (SakilaBean) fc.getApplication().evaluateExpressionGet(fc, "#{sakila}", SakilaBean.class);
			films = sb.getFilms();
		}		
		return films;
	}
	
	/**
	 * get category by id
	 *
	 * @return String  
	 */
	
	public String getCategoryId() {
		return categoryId;
	}
	
	/**
	 * set category by id
	 *
	 * @return void  
	 */
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * get category from film
	 *
	 * @return Category  
	 */
	
	public Category getCategory() {
		return category;
	}
	
	/**
	 * set category by Category
	 * @param Category category
	 * @return void  
	 */
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	// ####################### PAGINATION ####################### //
	
	/**
	 * get first film of List
	 * @return int  
	 */
	
	public int getFirst() {
		return first;
	}
	
	
	/**
	 * set first film of List
	 * @param inf first
	 * @return void  
	 */
	
	public void setFirst(int first) {
		this.first = first;
	}
	
	/**
	 * go one page forward
	 * @return String
	 */
	public String forward(){
		setFirst(first + 10);
		if(first > films.size() - 10){
			setFirst(films.size() - 10);
		}
		setPages();
		return null;
	}
	
	/**
	 * go one page backward
	 * 
	 * @return String  
	 */
	public String backward(){
		setFirst(first - 10);
		if(first < 1){
			setFirst(0);
		}
		setPages();
		return null;
	}
	
	/**
	 * jump to selected page
	 * @param int pageNumber
	 * @return String
	 */
	public String gotoPage(int pageNumber){
		setFirst((pageNumber-1) * 10);
		setPages();
		return null;
	}
}
