package com.strival.movie.vo;

import java.util.Date;

/**
 * Created by xinghai on 2015/12/18.
 */
public class ArticleVO {
    private long id;
    private String coverUrl;
    private String title;
    private String createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

        ArticleVO articleVO = (ArticleVO) o;

        if (id != articleVO.id) return false;
        if (browseNum != articleVO.browseNum) return false;
        if (!coverUrl.equals(articleVO.coverUrl)) return false;
        if (!title.equals(articleVO.title)) return false;
        return createTime.equals(articleVO.createTime);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + coverUrl.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + (int) (browseNum ^ (browseNum >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "id=" + id +
                ", coverUrl='" + coverUrl + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", browseNum=" + browseNum +
                '}';
    }
}
