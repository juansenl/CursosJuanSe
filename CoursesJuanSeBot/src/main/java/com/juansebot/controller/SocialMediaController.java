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

import com.juansebot.model.SocialMedia;
import com.juansebot.service.SocialMediaService;
import com.juansebot.util.CustomErrorType;


@Controller
@RequestMapping("/v1")
public class SocialMediaController {

	
	@Autowired
	private SocialMediaService _socialMediaService;

	//GET
	@RequestMapping(value="/socialMedias", method= RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(@RequestParam(value="name", required=false) String name){
		
		
		List <SocialMedia> socialMedias = new ArrayList<>();

		
		if(name == null) {
			socialMedias = _socialMediaService.FindAllSocialMedias();
			if(socialMedias.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
		
			return new ResponseEntity<List<SocialMedia>>(socialMedias,HttpStatus.OK);
		}else {
			
			SocialMedia socialMedia = _socialMediaService.FindSocialMediaByName(name);
			
			if(socialMedia == null) {
				new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			socialMedias.add(socialMedia);
			
			
			return new ResponseEntity<List<SocialMedia>> (socialMedias, HttpStatus.OK);
		}
	
		
		
	}
	
	//GET
	@RequestMapping(value="/socialMedias/{id}" , method=RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaById(@PathVariable("id") Long idSocialMedia){
		
		if(idSocialMedia== null || idSocialMedia <=0) {
			return new ResponseEntity(new CustomErrorType("IdSocialMedia is required"),HttpStatus.CONFLICT);
		}
		
		SocialMedia socialMedia = _socialMediaService.FindSocialMediaById(idSocialMedia);
		if(socialMedia == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SocialMedia>(socialMedia,HttpStatus.OK);
	}
	
	//POST
	@RequestMapping(value="/socialMedias", method= RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createSocialMedia (@RequestBody SocialMedia socialMedia, UriComponentsBuilder uriComponensBuilder){
	
		if(socialMedia.getName().isEmpty() || socialMedia.getName().equals(null)) {
			return new ResponseEntity(new CustomErrorType("SocialMedia name is required"),HttpStatus.CONFLICT);
		}
		
		if(_socialMediaService.FindSocialMediaByName(socialMedia.getName()) != null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		_socialMediaService.saveSocialMedia(socialMedia);
		SocialMedia socialMedia2 = _socialMediaService.FindSocialMediaByName(socialMedia.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponensBuilder.path("/v1/socialMedias/{id}")
				.buildAndExpand(socialMedia2.getIdSocialMedia())
				.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	
	//UPDATE
	@RequestMapping(value="/socialMedias/{id}", method= RequestMethod.PATCH , headers= "Accept=application/json")
	public ResponseEntity<?> updateSocialMedia(@PathVariable("id") Long idSocialMedia, @RequestBody SocialMedia socialMedia){
		
		if(idSocialMedia == null || idSocialMedia<=0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia is required"),HttpStatus.CONFLICT);

		}
		
		
		SocialMedia currentSocialMedia = _socialMediaService.FindSocialMediaById(idSocialMedia);
		if(currentSocialMedia == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		currentSocialMedia.setName(socialMedia.getName());
		currentSocialMedia.setIcon(socialMedia.getIcon());
		
		_socialMediaService.updateSocialMedia(currentSocialMedia);
		return new ResponseEntity<SocialMedia>(currentSocialMedia, HttpStatus.OK);
	}

	
	//DELETE
	@RequestMapping(value="/socialMedias/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
	public ResponseEntity<?> deleteSocialMedia(@PathVariable("id") Long idSocialMedia){
		
		if(idSocialMedia== null || idSocialMedia <=0) {
			return new ResponseEntity(new CustomErrorType("idSocialMedia is required"),HttpStatus.CONFLICT);
		}
		SocialMedia socialMedia = _socialMediaService.FindSocialMediaById(idSocialMedia);
		
		if(socialMedia == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		_socialMediaService.deleteSocialMedia(idSocialMedia);
		return new ResponseEntity<SocialMedia>(HttpStatus.OK);
	}
}
