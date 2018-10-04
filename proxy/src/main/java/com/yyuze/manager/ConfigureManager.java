package com.yyuze.manager;

import com.yyuze.entity.Jar;
import com.yyuze.entity.Route;

import java.io.InputStream;
import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/17
 */
public interface ConfigureManager {

    void createJar(Jar jar) throws Exception;

    List<Jar> findAllJars();

    List<Jar> findJarsByType(String type);

    void updateJar(Jar jar);

    void deleteJar(Jar jar);

    void createRoute(Route route);

    List<Route> findRoutesByType(String type);

    List<Route> findAllRoutes();

    void updateRoute(Route route);

    void deleteRoute(Route route);

    void uploadJar(InputStream inputStream);
}
