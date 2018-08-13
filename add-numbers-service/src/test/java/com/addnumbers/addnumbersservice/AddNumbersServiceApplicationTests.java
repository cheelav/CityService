package com.addnumbers.addnumbersservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.addnumbers.service.AddNumbersServiceApplication;
import com.addnumbers.service.model.SecurityQuestion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddNumbersServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddNumbersServiceApplicationTests {

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
				"http://localhost:"+port+"/",
				HttpMethod.GET, entity, String.class);
		
		Assert.assertTrue(response.getStatusCode()==HttpStatus.OK);
		
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		Assert.assertTrue(!root.path("numbers").isNull());
		Assert.assertTrue(root.path("answer").asInt()==0);
		
		//Test post request for right answer
		List<Integer> numbers = mapper.convertValue(root.path("numbers"), ArrayList.class);
		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
		
		SecurityQuestion question=new SecurityQuestion(root.path("questionText").asText(),numbers,sum);
		
		HttpEntity<SecurityQuestion> postentity = new HttpEntity<SecurityQuestion>(question, headers);
		
		ResponseEntity<String> postresponse = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, postentity, String.class);
		Assert.assertTrue(postresponse.getStatusCode()==HttpStatus.OK);
		
		//Test post request for wrong answer
		question=new SecurityQuestion(root.path("questionText").asText(),numbers,sum+1);
		postentity = new HttpEntity<SecurityQuestion>(question, headers);
		postresponse = restTemplate.exchange(
				createURLWithPort("/"),
				HttpMethod.PUT, postentity, String.class);
		Assert.assertTrue(postresponse.getStatusCode()==HttpStatus.BAD_REQUEST);	
		
		
	}
	


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	

}
