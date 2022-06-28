
package com.flashcall.thirdclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flashcall.thirdclient.model.ServerResult;
import com.flashcall.thirdclient.service.FlashCallService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FlashcallPlanCallbackController {
	
	@Autowired
	private FlashCallService flashCallService;
	
	//test
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ServerResult test() {
		System.out.println("test ---------------------------------->");
		return ServerResult.createBySuccess();
	}
	
	/**
	 * 批量回调通知
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/batchPlanCallback", method=RequestMethod.POST)
	public ServerResult batchPlanCallback(@RequestBody String jsonStr) {
		log.info("batchPlanCallback : " + jsonStr);
		//flashCallService.batchPlanCallback(jsonStr);
		return ServerResult.createBySuccess();
	}
	
	/**
	 * 单笔回调通知
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/planCallback", method=RequestMethod.POST)
	public ServerResult planCallback(@RequestBody String jsonStr) {
		log.info("planCallback : " + jsonStr);
		//flashCallService.planCallback(jsonStr);
		return ServerResult.createBySuccess();
		
	}
	
	
}
