package com.juansebot.service;

import java.util.List;

import com.juansebot.model.SocialMedia;
import com.juansebot.model.TeacherSocialMedia;

public interface SocialMediaService {

	
void saveSocialMedia (SocialMedia socialMedia);
	
	List <SocialMedia> FindAllSocialMedias(); 
	
	SocialMedia FindSocialMediaById(Long idSocialMedia);
	
	SocialMedia FindSocialMediaByName(String name);
	
	TeacherSocialMedia FindSocialMediaByIdAndName(Long IdSocialMedia, String nickname);
	
	void updateSocialMedia(SocialMedia socialMedia);
	
	void deleteSocialMedia(Long idSocialMedia);
	
	TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia);

}
