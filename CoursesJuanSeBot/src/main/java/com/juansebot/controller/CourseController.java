package com.juansebot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.juansebot.model.Course;
import com.juansebot.service.CourseService;
import com.juansebot.util.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class CourseController {
	
	@Autowired
	private CourseService _courseService;
	
	
	//GET
	@RequestMapping(value="/courses", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List <Course>> getCourses(@RequestParam(value="name", required=false) String name, @RequestParam(value="id_teacher", required=false) Long id_teacher){
		
		List <Course> courses = new ArrayList <>();
		
		if(id_teacher!= null) {
			courses = _courseService.FindByIdTeacher(id_teacher);
			if(courses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
		}
		
		if(name !=null ) {
				Course course = _courseService.FindCourseByName(name);
				if(course == null) {
					return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
				
			courses.add(course);
			}
			
		if(name != null && id_teacher != null) {
				courses = _courseService.FindAllCourses();
			if(courses.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			
		}
		
		return new ResponseEntity<List <Course>>(courses, HttpStatus.OK);
	
	}
	
	//GET BY ID
	@RequestMapping(value="/courses/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<Course> getCourseById (@PathVariable(value="id") Long id){
		
		Course course = _courseService.FindCourseById(id);
		
		if(course == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}
	
	//CREATE
	@RequestMapping(value="/course", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<?> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder){
		
		if(_courseService.FindCourseByName(course.getName()) != null) {
			return new ResponseEntity(new CustomErrorType("Unable to create, A course with name"+ course.getName() +
					" already exists" ),HttpStatus.CONFLICT);
		}
		
		_courseService.saveCourse(course);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/courses/{id}").buildAndExpand(course.getIdCourse()).toUri());
		return new ResponseEntity<String>(headers,HttpStatus.CREATED);
		
		}
		
	
	//UPDATE
	@RequestMapping(value="/courses/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateCourse(@PathVariable(value="id") Long id, @RequestBody Course course){
		
		
		Course currentCourse = _courseService.FindCourseById(id);
		
		if(currentCourse == null) {
			return new ResponseEntity(new CustomErrorType("Unable to update. Id " + id + " not found"),HttpStatus.NOT_FOUND);
		}
		
		currentCourse.setName(course.getName());
		currentCourse.setProject(course.getProject());
		currentCourse.setThemes(course.getThemes());
		
		_courseService.updateCourse(currentCourse);
		return new ResponseEntity<Course>(currentCourse,HttpStatus.OK);
	}
	
	
	//DELETE
	@RequestMapping(value="/courses/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteCourse(@PathVariable(value="id") Long id) {
		
		Course course = _courseService.FindCourseById(id);
		
		if(course == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Id " + id + " not found"),HttpStatus.NOT_FOUND);

		}
		
		_courseService.deleteCourse(id);
		
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		
	}
	
	
	
}
