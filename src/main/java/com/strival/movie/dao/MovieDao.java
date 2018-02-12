package com.strival.movie.dao;

import com.strival.movie.po.FormSave;
import com.strival.movie.po.Movie;
import com.strival.movie.po.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
@Repository
public interface MovieDao extends JpaRepository<Movie,Long> {
    @Query("from Movie m where m.name like %?1%")
    List<Movie> findByName(String name);

    @Query("from Movie m where m.name like %?1% or m.year like %?1% or m.author like %?1% or m.genre like %?1%")
    List<Movie> findByKeyword(String keyword);

    List<Movie> findByMovieCategory(MovieCategory movieCategory);
}
