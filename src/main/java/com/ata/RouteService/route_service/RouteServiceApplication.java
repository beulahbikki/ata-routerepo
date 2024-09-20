package com.ata.RouteService.route_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
/**
*
*
* @author Jon Doe
*/

public class RouteServiceApplication {
	/**
	*
	*
	* @author Jon Doe
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(RouteServiceApplication.class, args);
	}

}
