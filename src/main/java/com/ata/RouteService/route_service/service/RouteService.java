package com.ata.RouteService.route_service.service;

import java.util.List;

import com.ata.RouteService.route_service.entity.RouteEntity;

public interface RouteService {
    RouteEntity saveRoute(RouteEntity route);
    List<RouteEntity> getAllRoutes();
    RouteEntity getRouteById(Long id);
    void deleteRouteById(Long id);
}