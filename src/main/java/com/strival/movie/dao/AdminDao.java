package com.strival.movie.dao;

import com.strival.movie.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by xinghai on 2015/12/16.
 */
@Repository
public interface AdminDao extends JpaRepository<Admin,Long> {
    Admin findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Admin a set a.password= ?2 where a.id= ?1")
    void updatePassword(long id,String password);
}
