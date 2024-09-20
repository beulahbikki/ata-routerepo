package com.ata.RouteService.route_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ata.RouteService.route_service.entity.RouteEntity;


public interface RouteRepository extends JpaRepository <RouteEntity, Long> {

}
