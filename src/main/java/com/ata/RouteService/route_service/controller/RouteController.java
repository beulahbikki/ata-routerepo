package com.ata.RouteService.route_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ata.RouteService.route_service.entity.RouteEntity;
import com.ata.RouteService.route_service.service.RouteService;

@RestController
@RequestMapping("/routes")
/**
*
*
* @author Jon Doe
*/
public class RouteController {
	
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @PostMapping("/add")
    public ResponseEntity<RouteEntity> createRoute(@RequestBody RouteEntity route) {
        logger.info("Creating route: {}", route);
        RouteEntity createdRoute = routeService.saveRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RouteEntity>> getAllRoutes() {
        logger.info("Fetching all routes");
        List<RouteEntity> routes = routeService.getAllRoutes();
        if (routes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteEntity> getRouteById(@PathVariable Long id) {
        logger.info("Fetching route with id: {}", id);
        RouteEntity route = routeService.getRouteById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable Long id) {
        logger.info("Deleting route with id: {}", id);
        routeService.deleteRouteById(id);
        return new ResponseEntity<>("Route deleted successfully", HttpStatus.OK);
    }
}
