package com.ata.RouteService.route_serviceservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ata.RouteService.route_service.entity.RouteEntity;
import com.ata.RouteService.route_service.exception.RouteNotFoundException;
import com.ata.RouteService.route_service.repository.RouteRepository;
import com.ata.RouteService.route_service.serviceimpl.RouteServiceImpl;

class RouteServiceImplTest {

    @InjectMocks
    private RouteServiceImpl routeService;

    @Mock
    private RouteRepository routeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveRoute() {
        RouteEntity route = new RouteEntity();
        when(routeRepository.save(route)).thenReturn(route);

        RouteEntity savedRoute = routeService.saveRoute(route);
        assertEquals(route, savedRoute);
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void testGetAllRoutes() {
        List<RouteEntity> routes = new ArrayList<>();
        routes.add(new RouteEntity());
        when(routeRepository.findAll()).thenReturn(routes);

        List<RouteEntity> retrievedRoutes = routeService.getAllRoutes();
        assertEquals(routes, retrievedRoutes);
        verify(routeRepository, times(1)).findAll();
    }

    @Test
    void testGetRouteById_Success() {
        RouteEntity route = new RouteEntity();
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        RouteEntity retrievedRoute = routeService.getRouteById(1L);
        assertEquals(route, retrievedRoute);
        verify(routeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetRouteById_NotFound() {
        when(routeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RouteNotFoundException.class, () -> {
            routeService.getRouteById(1L);
        });
        verify(routeRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteRouteById_Success() {
        when(routeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(routeRepository).deleteById(1L);

        routeService.deleteRouteById(1L);
        verify(routeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteRouteById_NotFound() {
        when(routeRepository.existsById(1L)).thenReturn(false);

        assertThrows(RouteNotFoundException.class, () -> {
            routeService.deleteRouteById(1L);
        });
        verify(routeRepository, never()).deleteById(1L);
    }
}
