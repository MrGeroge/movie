package com.strival.movie.dao;

import com.strival.movie.po.Account;
import com.strival.movie.po.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/11/9.
 */
@Repository
public interface AccountProfileDao extends JpaRepository<AccountProfile,Long> {
    AccountProfile findByAccount(Account account);
}
