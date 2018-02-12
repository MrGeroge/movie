package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/28.
 */
public class JudgerVO {
    private long id;
    private String summary;
    private String avatarUrl;
    private long periodNum;

    public long getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(long periodNum) {
        this.periodNum = periodNum;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JudgerVO judgerVO = (JudgerVO) o;

        if (id != judgerVO.id) return false;
        if (periodNum != judgerVO.periodNum) return false;
        if (!summary.equals(judgerVO.summary)) return false;
        return avatarUrl.equals(judgerVO.avatarUrl);

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
        return "JudgerVO{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", periodNum=" + periodNum +
                '}';
    }
}
