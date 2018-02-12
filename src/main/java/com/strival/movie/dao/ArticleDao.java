package com.strival.movie.dao;

import com.strival.movie.po.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by xinghai on 2015/12/18.
 */
@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {
    @Modifying
    @Transactional
    @Query("update Article a set a.browseNum= a.browseNum+1 where a.id= ?1")
    void updateBrowseNum(long id);

}
