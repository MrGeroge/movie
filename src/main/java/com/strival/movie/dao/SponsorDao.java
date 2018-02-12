package com.strival.movie.dao;

import com.strival.movie.po.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/11/9.
 */
@Repository
public interface SponsorDao extends JpaRepository<Sponsor,Long> {
}
