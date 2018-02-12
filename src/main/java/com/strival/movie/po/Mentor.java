package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="mentor_name",nullable = false)
    private String name;
    @Column(name="mentor_position",nullable = false)
    private String position;
    @Lob
    @Column(name="mentor_summary",nullable = false)
    private String summary;
    @Column(name="avatar_url",nullable = false)
    private String avatarUrl;

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

        Mentor mentor = (Mentor) o;

        if (id != mentor.id) return false;
        if (!name.equals(mentor.name)) return false;
        if (!position.equals(mentor.position)) return false;
        if (!summary.equals(mentor.summary)) return false;
        return avatarUrl.equals(mentor.avatarUrl);

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
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", summary='" + summary + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
