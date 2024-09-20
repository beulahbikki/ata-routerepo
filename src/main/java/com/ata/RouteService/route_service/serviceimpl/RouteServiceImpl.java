package com.ata.RouteService.route_service.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.RouteService.route_service.entity.RouteEntity;
import com.ata.RouteService.route_service.exception.RouteNotFoundException;
import com.ata.RouteService.route_service.repository.RouteRepository;
import com.ata.RouteService.route_service.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public RouteEntity saveRoute(RouteEntity route) {
        logger.info("Saving route: {}", route);
        return routeRepository.save(route);
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        logger.info("Retrieving all routes");
        return routeRepository.findAll();
    }

    @Override
    public RouteEntity getRouteById(Long id) {
        logger.info("Retrieving route with id: {}", id);
        return routeRepository.findById(id)
            .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
    }

    @Override
    public void deleteRouteById(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RouteNotFoundException("Route not found with id: " + id);
        }
        logger.info("Deleting route with id: {}", id);
        routeRepository.deleteById(id);
    }
}
