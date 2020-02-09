package com.juansebot.service;

import java.util.List;

import com.juansebot.model.Teacher;

public interface TeacherService {

	
void saveTeacher(Teacher teacher);
	
	List <Teacher> findAllTeachers();
	
	Teacher findTeacherById(Long idTeacher);
	
	Teacher findByName(String name);
	
	void deleteTeacherById(Long idTeacher);
	
	void updateTeacher (Teacher teacher);
	
}
