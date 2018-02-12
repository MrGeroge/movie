package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/18.
 */
public class ArticleDetailVO {
    private String title;
    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDetailVO that = (ArticleDetailVO) o;

        if (!title.equals(that.title)) return false;
        return detail.equals(that.detail);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + detail.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ArticleDetailVO{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
