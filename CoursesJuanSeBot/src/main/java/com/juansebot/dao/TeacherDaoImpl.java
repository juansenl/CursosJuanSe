package com.juansebot.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.juansebot.model.Teacher;
import com.juansebot.model.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {

	
	@Override
	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().persist(teacher);
		
	}

	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Teacher").list();
	}

	@Override
	public Teacher findTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		return (Teacher) getSession().get(Teacher.class, idTeacher);
	}
	

	@Override
	public void deleteTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		
		Teacher teacher = findTeacherById(idTeacher);
		if(teacher!=null) {
			
			Iterator <TeacherSocialMedia> it = teacher.getTeacherSocialMedia().iterator(); 
			while (it.hasNext()) {
				TeacherSocialMedia tsm = it.next();
				it.remove();
				getSession().delete(tsm);
				
			}
			teacher.getTeacherSocialMedia().clear();
			getSession().delete(teacher);
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().update(teacher);
		
	}

	@Override
	public Teacher findByName(String name) {
		// TODO Auto-generated method stub
		return (Teacher) getSession().createQuery(
				"from Teacher where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	
	
	
}
