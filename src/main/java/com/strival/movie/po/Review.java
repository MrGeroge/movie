package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="review_name",nullable = false)
    private String name;
    @Column(name="review_position",nullable = false)
    private String position;
    @Lob
    @Column(name="review_review",nullable = false)
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

        Review review = (Review) o;

        if (id != review.id) return false;
        if (!name.equals(review.name)) return false;
        if (!position.equals(review.position)) return false;
        if (!summary.equals(review.summary)) return false;
        return avatarUrl.equals(review.avatarUrl);

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
        return "Review{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", summary='" + summary + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
