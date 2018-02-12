package com.strival.movie.dao;


import com.strival.movie.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by xinghai on 2015/12/16.
 */
@Repository
public interface TagDao extends JpaRepository<Tag,Long> {
    @Modifying
    @Transactional
    @Query("update Tag a set a.name= ?2 where a.id= ?1")
    void updateName(long id,String name);
}
