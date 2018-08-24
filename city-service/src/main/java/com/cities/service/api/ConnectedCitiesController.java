package com.cities.service.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.service.core.ConnectedCitiesService;

@RestController

public class ConnectedCitiesController {

	@Autowired
	ConnectedCitiesService connectedCities;
	
    //@GetMapping("/origin/{origin}/destination/{destination}")
	@RequestMapping("/connected/origin/{origin}/destination/{destination}")
    public boolean getConnectionWithPath(@PathVariable String origin, @PathVariable String destination){
        return connectedCities.areCitiesConnected(origin, destination);
    }
 
	@RequestMapping("/connected")
    public boolean getConnectionWithParam(@RequestParam("origin") String origin, @RequestParam("destination") String destination){
        return connectedCities.areCitiesConnected(origin, destination);
    }
    

}
