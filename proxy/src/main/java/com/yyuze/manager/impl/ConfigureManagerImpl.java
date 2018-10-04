package com.yyuze.manager.impl;

import com.yyuze.entity.Jar;
import com.yyuze.entity.Route;
import com.yyuze.manager.ConfigureManager;
import com.yyuze.repository.JarRepository;
import com.yyuze.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/17
 */
@Component
public class ConfigureManagerImpl implements ConfigureManager {

    @Autowired
    private JarRepository jarRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void createJar(Jar jar) throws Exception {
        if (this.jarRepository.getJarByType(jar.getType()).size() != 0) {
            throw new Exception("create failed: jar of this type is existed");
        }
        else {
            this.jarRepository.save(jar);
        }
    }

    @Override
    public List<Jar> findAllJars() {
        return this.jarRepository.loadProcessJars();
    }

    @Override
    public List<Jar> findJarsByType(String type) {
        return this.jarRepository.getJarByType(type);
    }

    @Override
    public void updateJar(Jar jar) {
        jarRepository.save(jar);
    }

    @Override
    public void deleteJar(Jar jar) {
        jarRepository.delete(jar);
    }

    @Override
    public void createRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public List<Route> findRoutesByType(String type) {
        return routeRepository.getRoutesByType(type);
    }

    @Override
    public List<Route> findAllRoutes() {
        return routeRepository.loadRoutes();
    }

    @Override
    public void updateRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }

    @Override
    public void uploadJar(InputStream inputStream) {

    }
}
