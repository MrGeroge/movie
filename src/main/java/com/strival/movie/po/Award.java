package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/27.
 */
@Entity
@Table(name="tb_award")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="award_category",nullable = false)
    private String category;
    @Column(name="award_prize",nullable = false)
    private String prize;
    @Column(name="award_number",nullable = false)
    private int number;
    @Column(name="award_description",nullable = false)
    private String description;
    @Column(name="award_periodNum",nullable = false)
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

        Award award = (Award) o;

        if (id != award.id) return false;
        if (number != award.number) return false;
        if (periodNum != award.periodNum) return false;
        if (!category.equals(award.category)) return false;
        if (!prize.equals(award.prize)) return false;
        return description.equals(award.description);

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
        return "Award{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", prize='" + prize + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", periodNum=" + periodNum +
                '}';
    }
}

