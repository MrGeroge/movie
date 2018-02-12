package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/27.
 */
@Entity
@Table(name="tb_period")
public class Period {
    @Id
   private long periodNum;
    @Column(name = "year",nullable = false)
    private String year;

    public long getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(long periodNum) {
        this.periodNum = periodNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (periodNum != period.periodNum) return false;
        return year.equals(period.year);

    }

    @Override
    public int hashCode() {
        int result = (int) (periodNum ^ (periodNum >>> 32));
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "periodNum=" + periodNum +
                ", year='" + year + '\'' +
                '}';
    }
}
