package com.strival.movie.dao;

import com.strival.movie.po.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinghai on 2015/12/27.
 */
@Repository
public interface AwardDao extends JpaRepository<Award,Long> {
    List<Award> findByPeriodNum(long periodNum);
}
