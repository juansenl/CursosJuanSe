package com.juansebot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juansebot.dao.TeacherDao;
import com.juansebot.model.Teacher;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{

	
	@Autowired
	private TeacherDao _teacherDao;
	
	@Override
	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_teacherDao.saveTeacher(teacher);
	}

	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		return _teacherDao.findAllTeachers();
	}

	@Override
	public Teacher findTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		return _teacherDao.findTeacherById(idTeacher);
	}

	@Override
	public Teacher findByName(String name) {
		// TODO Auto-generated method stub
		return _teacherDao.findByName(name);
	}

	@Override
	public void deleteTeacherById(Long idTeacher) {
		// TODO Auto-generated method stub
		_teacherDao.deleteTeacherById(idTeacher);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		_teacherDao.updateTeacher(teacher);
	}

}
