package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/28.
 */
public class AwardVO {
    private long id;
    private String category; //奖项类别
    private String prize;  //奖项
    private int number; //奖项
    private String description;//
    private long periodNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(long periodNum) {
        this.periodNum = periodNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardVO awardVO = (AwardVO) o;

        if (id != awardVO.id) return false;
        if (number != awardVO.number) return false;
        if (periodNum != awardVO.periodNum) return false;
        if (!category.equals(awardVO.category)) return false;
        if (!prize.equals(awardVO.prize)) return false;
        return description.equals(awardVO.description);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + category.hashCode();
        result = 31 * result + prize.hashCode();
        result = 31 * result + number;
        result = 31 * result + description.hashCode();
        result = 31 * result + (int) (periodNum ^ (periodNum >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AwardVO{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", prize='" + prize + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", periodNum=" + periodNum +
                '}';
    }
}
