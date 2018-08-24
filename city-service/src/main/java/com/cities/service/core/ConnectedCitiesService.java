package com.cities.service.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.cities.service.model.ConnectedCity;

@Component
public class ConnectedCitiesService {

	//private  static HashMap<String,String> connectedCities=new HashMap<>();
	private  ArrayList<ConnectedCity> connectedCities= new ArrayList();
	
	@Autowired
	private ResourceLoader  resourceLoader;
	
	@PostConstruct
	public void  init()
	{
		try {
			Resource resource = resourceLoader.getResource("classpath:cities.csv");
			//File file = ResourceUtils.getFile("classpath:cities.csv");
			BufferedReader in = new BufferedReader(new FileReader(resource.getFile()));
			String line;
			while ((line = in.readLine()) != null) {
				String columns[] = line.split(",");
				//connectedCities.put(columns[0], columns[1]);
				connectedCities.add(new ConnectedCity(columns[0], columns[1]));

			}
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public  boolean areCitiesConnected(String origin, String destination)
	{

		ConnectedCity cityPair=new ConnectedCity(origin,destination);
		for(ConnectedCity connectedCity: connectedCities)
		{
			if(connectedCity.getOrigin().equalsIgnoreCase(origin) && 
					connectedCity.getDestination().equalsIgnoreCase(destination) ) {
				return true;
			}
		}
		
		return false;
	}
	
}
