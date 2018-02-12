package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "start_time",nullable = false)
    private String startTime;
    @Column(name = "direction",nullable = false)
    private String direction;
    @Column(name = "pattern",nullable = false)
    private String pattern;
    @Column(name = "mentor_name",nullable = false)
    private String mentorName ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (!startTime.equals(course.startTime)) return false;
        if (!direction.equals(course.direction)) return false;
        if (!pattern.equals(course.pattern)) return false;
        return mentorName.equals(course.mentorName);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + startTime.hashCode();
        result = 31 * result + direction.hashCode();
        result = 31 * result + pattern.hashCode();
        result = 31 * result + mentorName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", direction='" + direction + '\'' +
                ", pattern='" + pattern + '\'' +
                ", mentorName='" + mentorName + '\'' +
                '}';
    }
}
