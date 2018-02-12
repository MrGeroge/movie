package com.strival.movie.dao;

import com.strival.movie.po.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by xinghai on 2015/11/9.
 */
@Repository
public interface FormDao extends JpaRepository<Form,Long> {
    @Modifying
    @Transactional
    @Query("update Form a set a.controlDesc= ?2,a.formName= ?3 where a.id= ?1")
    void updateControlDescAndFormName(long id,String controlDesc,String formName);

    Form findByFormName(String formName);

}
