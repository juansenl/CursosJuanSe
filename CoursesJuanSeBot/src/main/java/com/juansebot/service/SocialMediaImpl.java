package com.juansebot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juansebot.dao.SocialMediaDao;
import com.juansebot.model.SocialMedia;
import com.juansebot.model.TeacherSocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaImpl implements SocialMediaService{

	@Autowired
	private SocialMediaDao _socialMediaDao;
	
	
	@Override
	public void saveSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.saveSocialMedia(socialMedia);
	}

	@Override
	public List<SocialMedia> FindAllSocialMedias() {
		// TODO Auto-generated method stub
		return _socialMediaDao.FindAllSocialMedias();
	}

	@Override
	public SocialMedia FindSocialMediaById(Long idSocialMedia) {
		// TODO Auto-generated method stub
		return _socialMediaDao.FindSocialMediaById(idSocialMedia);
	}

	@Override
	public SocialMedia FindSocialMediaByName(String name) {
		// TODO Auto-generated method stub
		return _socialMediaDao.FindSocialMediaByName(name);
	}

	@Override
	public TeacherSocialMedia FindSocialMediaByIdAndName(Long IdSocialMedia, String nickname) {
		// TODO Auto-generated method stub
		return _socialMediaDao.FindSocialMediaByIdAndName(IdSocialMedia, nickname);
	}

	@Override
	public void updateSocialMedia(SocialMedia socialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.updateSocialMedia(socialMedia);
		
	}

	@Override
	public void deleteSocialMedia(Long idSocialMedia) {
		// TODO Auto-generated method stub
		_socialMediaDao.deleteSocialMedia(idSocialMedia);
	}

	@Override
	public TeacherSocialMedia findSocialMediaByIdTeacherAndIdSocialMedia(Long idTeacher, Long idSocialMedia) {
		// TODO Auto-generated method stub
		return _socialMediaDao.findSocialMediaByIdTeacherAndIdSocialMedia(idTeacher, idSocialMedia);
	}
}
