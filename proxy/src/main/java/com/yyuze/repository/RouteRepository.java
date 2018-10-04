package com.yyuze.repository;

import com.yyuze.entity.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */

@Repository
public interface RouteRepository extends CrudRepository<Route, String> {

    @Query("select route from Route route")
    List<Route> loadRoutes();

    @Query("select route.token from Route route")
    List<String> loadTokens();

    @Query("select route from Route route where route.token=:token")
    Route getRouteByToken(@Param("token") String token);

    @Query("select route from Route route where route.type=:type")
    List<Route> getRoutesByType(@Param("type") String type);

}
