package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/28.
 */
public class PeriodVO {
    private long periodNum;
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

        PeriodVO periodVO = (PeriodVO) o;

        if (periodNum != periodVO.periodNum) return false;
        return year.equals(periodVO.year);

    }

    @Override
    public int hashCode() {
        int result = (int) (periodNum ^ (periodNum >>> 32));
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PeriodVO{" +
                "periodNum=" + periodNum +
                ", year='" + year + '\'' +
                '}';
    }
}
