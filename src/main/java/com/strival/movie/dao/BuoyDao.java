package com.strival.movie.dao;

import com.strival.movie.po.Buoy;
import com.strival.movie.po.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/12/19.
 */
@Repository
public interface BuoyDao extends JpaRepository<Buoy,Long> {

}
