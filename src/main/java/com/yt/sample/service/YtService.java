package com.yt.sample.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YtService {

	/*
	 * Author: Utkarsh Kumar Sharma
	 * Date: 11-10-2020
	 */
	@Value("${api_key}")
	private String api_key;
	
	private RestTemplate restTemplate;

	public YtService() {
		restTemplate = new RestTemplate();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(YtService.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getListOfVedios(String searchParameter) {
		List<String> listOfSearchResult = new ArrayList<>();
		try {
			String apiKey = api_key;
			String url = "https://www.googleapis.com/youtube/v3/search";
			URI uri = UriComponentsBuilder.fromUriString(url)
					.queryParam("part", "snippet")
					.queryParam("maxResults", "20")
					.queryParam("key", apiKey)
					.queryParam("q", searchParameter)
					.build().toUri();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Accept", "application/json");

			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders); 
			ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Map.class);

			if(response.getStatusCode().equals(HttpStatus.OK)) {
				LOGGER.info("Response got with status SUCCESS");
				Map<String, Object> body = response.getBody();
				ArrayList<?> listOfItems = (ArrayList<?>) body.get("items");
				for (Object object : listOfItems) {
					if (object instanceof Map<?, ?>) {
						System.out.println();
						Map<String, Object> objectToMap = (Map<String, Object>) object;
						Map<String, Object> vedioIdDetails = (Map<String, Object>) objectToMap.get("id");
						String vedioId = (String) vedioIdDetails.get("videoId");
						listOfSearchResult.add("https://www.youtube.com/watch?v="+vedioId);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.info("Exception : "+ e);
		}
		return listOfSearchResult;
	}
	
}
