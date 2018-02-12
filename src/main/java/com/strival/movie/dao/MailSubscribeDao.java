package com.strival.movie.dao;

import com.strival.movie.po.Account;
import com.strival.movie.po.MailSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinghai on 2015/11/9.
 */
@Repository
public interface MailSubscribeDao extends JpaRepository<MailSubscribe,Long> {
    int countByEmail(String email);
    MailSubscribe findByEmail(String email);
}
