package com.juansebot.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.juansebot.model.Course;

@Repository
@Transactional
public class CourseDaoImpl extends AbstractSession implements CourseDao{

	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
		getSession().persist(course);
		
	}

	@Override
	public List<Course> FindAllCourses() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Course").list();
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		getSession().update(course);
	}

	@Override
	public void deleteCourse(Long idCourse) {
		// TODO Auto-generated method stub
		Course course = FindCourseById(idCourse);
		
		if(course != null){
			getSession().delete(course);
		}
		
	}

	@Override
	public Course FindCourseById(Long idCourse) {
		// TODO Auto-generated method stub
		return (Course) getSession().get(Course.class, idCourse);
	}

	@Override
	public List<Course> FindByIdTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return (List<Course>) getSession().createQuery(
				"from Course c join c.teacher t where t.idTeacher = :idTeacher")
				.setParameter("idTeacher", idTeacher).list();
	}

	@Override
	public Course FindCourseByName(String name) {
		// TODO Auto-generated method stub
		return (Course) getSession().createQuery(
				"from Course where name = :name")
				.setParameter("name", name);
	}

	
	
	
}
