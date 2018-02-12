package com.strival.movie.dao;

import com.strival.movie.po.Account;
import com.strival.movie.po.Form;
import com.strival.movie.po.FormApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

/**
 * Created by xinghai on 2015/11/9.
 */
@Repository
public interface FormApplyDao extends JpaRepository<FormApply,Long> {
    List<FormApply> findByAccount(Account account);

    Page<FormApply> findByForm(Form form,Pageable pageable);

    @Modifying
    @Transactional
    @Query("update FormApply a set a.status= ?3 where a.account.id= ?1 and a.form.id= ?2")
    void updateStatus(long account_id,long form_id,int status);

    FormApply findByFormAndAccount(Form form,Account account);
}
