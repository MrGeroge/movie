package com.strival.movie.dao;

import com.strival.movie.po.MapLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
@Repository
public interface MapLightDao extends JpaRepository<MapLight,Long> {
    @Modifying
    @Transactional
    @Query("update MapLight a set a.status= ?2 where a.id= ?1")
    void updateStatus(long id,boolean status);

    List<MapLight> findByStatus(boolean status);
}
