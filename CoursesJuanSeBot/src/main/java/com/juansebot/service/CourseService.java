package com.juansebot.service;

import java.util.List;

import com.juansebot.model.Course;

public interface CourseService {

void saveCourse(Course course);
	
	List <Course> FindAllCourses(); 
	
	List <Course> FindByIdTeacher(Long idTeacher);
	
	Course FindCourseById(Long idCourse);
	
	Course FindCourseByName(String name);
	
	void updateCourse(Course course);
	
	void deleteCourse(Long idCourse);
	
	
}
