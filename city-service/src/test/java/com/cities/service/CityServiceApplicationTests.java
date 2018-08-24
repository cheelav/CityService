package com.cities.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	
	@Test
	public void testCreateQuestion() throws Exception{

		HttpEntity<String> entity = new HttpEntity<String>(null,headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:"+port+"/connected?origin=Norwalk&destination=stratford",
				HttpMethod.GET, entity, String.class);
		
		Assert.assertTrue(response.getBody().equals("true"));
		
		response = restTemplate.exchange(
				"http://localhost:"+port+"/connected?origin=Norwalk&destination=milford",
				HttpMethod.GET, entity, String.class);
		
		Assert.assertTrue(response.getBody().equals("false"));
	}
	


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}	

}
