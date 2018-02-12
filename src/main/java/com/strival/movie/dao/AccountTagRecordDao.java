package com.strival.movie.dao;

import com.strival.movie.po.Account;
import com.strival.movie.po.AccountProfile;
import com.strival.movie.po.AccountTagRecord;
import com.strival.movie.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by xinghai on 2015/12/17.
 */
@Repository
public interface AccountTagRecordDao extends JpaRepository<AccountTagRecord,Long> {
    List<AccountTagRecord> findByAccount(Account account);
    Page<AccountTagRecord> findByTag(Tag tag,Pageable pageable);
    @Query("select count(*) from AccountTagRecord a where a.tag.id =?1")
    long findByTagId(long tagid);
}
