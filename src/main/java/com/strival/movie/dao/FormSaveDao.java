package com.strival.movie.dao;

import com.strival.movie.po.Account;
import com.strival.movie.po.Form;
import com.strival.movie.po.FormApply;
import com.strival.movie.po.FormSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by xinghai on 2015/12/17.
 */
@Repository
public interface FormSaveDao extends JpaRepository<FormSave,Long> {
    List<FormSave> findByAccount(Account account);

    @Modifying
    @Transactional
    @Query("delete from  FormSave a where a.account.id =?1 and a.form.id =?2")
    void deleteByAccountAndForm(long account_id,long form_id);

    @Modifying
    @Transactional
    @Query("update FormSave a set a.contentDesc= ?3 where a.account.id= ?1 and a.form.id= ?2")
    void updateContentDesc(long account_id,long form_id,String contentDesc);

    @Modifying
    @Transactional
    @Query("update FormSave a set a.status= ?3 where a.account.id= ?1 and a.form.id= ?2")
    void updateStatus(long account_id,long form_id,int status);

    FormSave findByFormAndAccount(Form form,Account account);
}
