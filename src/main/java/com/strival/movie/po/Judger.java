package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/27.
 */
@Entity
@Table(name="tb_judger")
public class Judger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Lob
    @Column(name="judger_summary",nullable = false)
    private String summary;
    @Column(name="avatar_url",nullable = false)
    private String avatarUrl;
    @Column(name="period_num",nullable = false)
    private long periodNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

        Judger judger = (Judger) o;

        if (id != judger.id) return false;
        if (periodNum != judger.periodNum) return false;
        if (!summary.equals(judger.summary)) return false;
        return avatarUrl.equals(judger.avatarUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + summary.hashCode();
        result = 31 * result + avatarUrl.hashCode();
        result = 31 * result + (int) (periodNum ^ (periodNum >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Judger{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", periodNum=" + periodNum +
                '}';
    }
}
