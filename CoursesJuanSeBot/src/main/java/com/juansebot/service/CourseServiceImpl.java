package com.juansebot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juansebot.dao.CourseDao;
import com.juansebot.model.Course;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

	
	@Autowired
	private CourseDao _courseDao;
	
	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
		_courseDao.saveCourse(course);;
		
	}

	@Override
	public List<Course> FindAllCourses() {
		// TODO Auto-generated method stub
		return _courseDao.FindAllCourses();
	}

	@Override
	public List<Course> FindByIdTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return _courseDao.FindByIdTeacher(idTeacher);
	}

	@Override
	public Course FindCourseById(Long idCourse) {
		// TODO Auto-generated method stub
		return _courseDao.FindCourseById(idCourse);
	}

	@Override
	public Course FindCourseByName(String name) {
		// TODO Auto-generated method stub
		return _courseDao.FindCourseByName(name);
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		_courseDao.updateCourse(course);
	}

	@Override
	public void deleteCourse(Long idCourse) {
		// TODO Auto-generated method stub
		_courseDao.deleteCourse(idCourse);
	}

}
