package com.strival.movie.dao;

import com.strival.movie.po.Account;
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
public interface AccountDao extends JpaRepository<Account,Long> {
    Account findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Account a set a.password= ?2 where a.id= ?1")
    void updatePassword(long id,String password);


    @Query("from Account a where a.username like %?1%")
    Page<Account> findByKeyword(String keyword,Pageable pageable);

    @Query("select count(*) from Account a where a.username like %?1%")
    long findByKeyword(String keyword);
}
