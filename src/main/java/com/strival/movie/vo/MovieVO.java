package com.strival.movie.vo;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
public class MovieVO {
    private long id;
    private long kindId;
    private String name; //影片名
    private String kind;//影片分类
    private String firstType;//首映类型
    private String attribute;
    private String year;//年代
    private String author; //作者
    private String genre; //体裁
    private String group; //制作班底
    private String description; //影片简介
    private String state; //导演阐述
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieVO movieVO = (MovieVO) o;

        if (id != movieVO.id) return false;
        if (kindId != movieVO.kindId) return false;
        if (!name.equals(movieVO.name)) return false;
        if (!kind.equals(movieVO.kind)) return false;
        if (!firstType.equals(movieVO.firstType)) return false;
        if (!attribute.equals(movieVO.attribute)) return false;
        if (!year.equals(movieVO.year)) return false;
        if (!author.equals(movieVO.author)) return false;
        if (!genre.equals(movieVO.genre)) return false;
        if (!group.equals(movieVO.group)) return false;
        if (!description.equals(movieVO.description)) return false;
        if (!state.equals(movieVO.state)) return false;
        return imageUrl.equals(movieVO.imageUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (kindId ^ (kindId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + kind.hashCode();
        result = 31 * result + firstType.hashCode();
        result = 31 * result + attribute.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + imageUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MovieVO{" +
                "id=" + id +
                ", kindId=" + kindId +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", firstType='" + firstType + '\'' +
                ", attribute='" + attribute + '\'' +
                ", year='" + year + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
