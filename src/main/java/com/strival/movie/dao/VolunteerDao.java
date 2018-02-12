package com.strival.movie.dao;

import com.strival.movie.po.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinghai on 2015/12/27.
 */
@Repository
public interface VolunteerDao extends JpaRepository<Volunteer,Long> {
    List<Volunteer> findByPeriodNum(long periodNum);
}
