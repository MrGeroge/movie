package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/19.
 */
public class MentorVO {
    private long id;
    private String name;
    private String position;
    private String summary;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MentorVO mentorVO = (MentorVO) o;

        if (id != mentorVO.id) return false;
        if (!name.equals(mentorVO.name)) return false;
        if (!position.equals(mentorVO.position)) return false;
        if (!summary.equals(mentorVO.summary)) return false;
        return avatarUrl.equals(mentorVO.avatarUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + summary.hashCode();
        result = 31 * result + avatarUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MentorVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", summary='" + summary + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
