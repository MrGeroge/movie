package com.strival.movie.dao;

import com.strival.movie.po.Course;
import com.strival.movie.po.Enter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xinghai on 2015/12/19.
 */
@Repository
public interface CourseDao extends JpaRepository<Course,Long> {
}
