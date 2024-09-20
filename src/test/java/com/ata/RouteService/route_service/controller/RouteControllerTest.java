package com.ata.RouteService.route_service.controller;

import com.ata.RouteService.route_service.entity.RouteEntity;
import com.ata.RouteService.route_service.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateRoute() {
        RouteEntity route = new RouteEntity();
        when(routeService.saveRoute(route)).thenReturn(route);

        ResponseEntity<RouteEntity> response = routeController.createRoute(route);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(route, response.getBody());
        verify(routeService, times(1)).saveRoute(route);
    }

    @Test
    void testGetAllRoutes() {
        List<RouteEntity> routes = new ArrayList<>();
        routes.add(new RouteEntity());
        when(routeService.getAllRoutes()).thenReturn(routes);

        ResponseEntity<List<RouteEntity>> response = routeController.getAllRoutes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routes, response.getBody());
        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    void testGetAllRoutes_NoContent() { 
        when(routeService.getAllRoutes()).thenReturn(new ArrayList<>());

        ResponseEntity<List<RouteEntity>> response = routeController.getAllRoutes();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    void testGetRouteById() {
        RouteEntity route = new RouteEntity();
        when(routeService.getRouteById(1L)).thenReturn(route);

        ResponseEntity<RouteEntity> response = routeController.getRouteById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(route, response.getBody());
        verify(routeService, times(1)).getRouteById(1L);
    }

    @Test
    void testDeleteRouteById() {
        doNothing().when(routeService).deleteRouteById(1L);

        ResponseEntity<String> response = routeController.deleteRouteById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Route deleted successfully", response.getBody());
        verify(routeService, times(1)).deleteRouteById(1L);
    }
}
