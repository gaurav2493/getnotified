package com.jarvit.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jarvit.helper.Album;
import com.jarvit.helper.URLHtml;
import com.shephertz.app42.paas.sdk.java.App42API;
import com.shephertz.app42.paas.sdk.java.push.PushNotification;
import com.shephertz.app42.paas.sdk.java.push.PushNotificationService;

@Controller
public class MobileRequestController {

	@Autowired
	DataSource dataSource;
	
	private final static String API_KEY="54ab3a38257c69510bee581ee8a9c35b12eae44fd67042410283f05271f8d873";
	private final static String SECRET_KEY="b10099101b6be660e8ded24bbef7d819b4266fce92b0f95c25ba917bc12874c4";

	@ResponseBody
	@RequestMapping(value = "/post/music")
	public String getMusicData(@RequestParam("artist") String artist) {
		
		Album alb = new URLHtml(artist).result;
		
		App42API.initialize(API_KEY, SECRET_KEY);
		PushNotificationService pushNotificationService = App42API.buildPushNotificationService();    
		PushNotification pushNotification = pushNotificationService.sendPushMessageToUser("pappu","new Album released "+alb.getAlbumTitle());
		System.out.println("hello");
		return "new Album released "+alb.getAlbumTitle();
	}

}
