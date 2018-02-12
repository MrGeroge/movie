package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/22.
 */
public class ArticleSimpleVO {
    private String title;
    private String coverUrl;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleSimpleVO that = (ArticleSimpleVO) o;

        if (!title.equals(that.title)) return false;
        if (!coverUrl.equals(that.coverUrl)) return false;
        return content.equals(that.content);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + coverUrl.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ArticleSimpleVO{" +
                "title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
