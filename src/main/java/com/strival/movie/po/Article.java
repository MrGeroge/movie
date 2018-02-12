package com.strival.movie.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xinghai on 2015/12/18.
 */
@Entity
@Table(name="tb_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="cover_url",nullable = false)
    private String coverUrl;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="create_at",nullable = false)
    private Date createAt;
    @Lob
    @Column(name="content",nullable = false)
    private String content;
    @Column(name="browse_num",nullable = false)
    private long browseNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(long browseNum) {
        this.browseNum = browseNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (browseNum != article.browseNum) return false;
        if (!coverUrl.equals(article.coverUrl)) return false;
        if (!title.equals(article.title)) return false;
        if (!createAt.equals(article.createAt)) return false;
        return content.equals(article.content);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + coverUrl.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + createAt.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + (int) (browseNum ^ (browseNum >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", coverUrl='" + coverUrl + '\'' +
                ", title='" + title + '\'' +
                ", createAt=" + createAt +
                ", content='" + content + '\'' +
                ", browseNum=" + browseNum +
                '}';
    }
}
