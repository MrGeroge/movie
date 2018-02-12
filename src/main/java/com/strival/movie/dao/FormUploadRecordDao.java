package com.strival.movie.dao;

import com.strival.movie.po.FormUploadRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/12/27.
 */
@Repository
public interface FormUploadRecordDao extends JpaRepository<FormUploadRecord,Long> {
}
