package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/19.
 */
public class CourseVO {
    private long id;
    private String startTime;
    private String direction;
    private String pattern;
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

        CourseVO courseVO = (CourseVO) o;

        if (id != courseVO.id) return false;
        if (!startTime.equals(courseVO.startTime)) return false;
        if (!direction.equals(courseVO.direction)) return false;
        if (!pattern.equals(courseVO.pattern)) return false;
        return mentorName.equals(courseVO.mentorName);

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
        return "CourseVO{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", direction='" + direction + '\'' +
                ", pattern='" + pattern + '\'' +
                ", mentorName='" + mentorName + '\'' +
                '}';
    }
}
