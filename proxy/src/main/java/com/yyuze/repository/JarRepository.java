package com.yyuze.repository;

import com.yyuze.entity.Jar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/8/31
 */

@Repository
public interface JarRepository extends CrudRepository<Jar, String> {

    @Query("select jar from Jar jar")
    List<Jar> loadProcessJars();

    @Query("select jar from Jar jar where jar.type=:type")
    List<Jar> getJarByType(@Param("type") String type);

}
