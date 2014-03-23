package com.jarvit.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jarvit.helper.Album;
import com.jarvit.helper.URLHtml;

@Controller
public class MobileRequestController {

	@Autowired
	DataSource dataSource;

	@ResponseBody
	@RequestMapping(value = "/post/music")
	public String getMusicData(@RequestParam("artist") String artist) {
		
		Album alb = new URLHtml(artist).result;
		
		
		return "new Album released "+alb.getAlbumTitle();
	}

}
