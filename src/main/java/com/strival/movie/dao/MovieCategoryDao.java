package com.strival.movie.dao;

import com.strival.movie.po.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/12/20.
 */
@Repository
public interface MovieCategoryDao extends JpaRepository<MovieCategory,Long> {
    MovieCategory findByName(String name);
}
