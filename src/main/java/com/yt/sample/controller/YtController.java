package com.yt.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.sample.service.YtService;

@RestController
public class YtController {

	/*
	 * Author: Utkarsh Kumar Sharma
	 * Date: 11-10-2020
	 */
	
	@Autowired
	private YtService ytService;
	
	@GetMapping(value = "/getListOfVedios")
	public List<String> getListOfVedios(@RequestParam String searchParameter) {
		return ytService.getListOfVedios(searchParameter);
	}
}
