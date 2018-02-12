package com.strival.movie.dao;

import com.strival.movie.po.Review;
import com.strival.movie.po.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
@Repository
public interface ReviewDao extends JpaRepository<Review,Long> {
    List<Review> findByPosition(String position);
}
