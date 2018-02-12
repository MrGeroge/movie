package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="film_name",nullable = false)
    private String name; //影片名
    @Column(name="first_type",nullable = false)
    private String firstType;//首映类型
    @Column(name="film_attribute",nullable = false)
    private String attribute; //属性串
    @Column(name="year",nullable = false)
    private String year;//年代
    @Column(name="author",nullable = false)
    private String author; //作者
    @Column(name="genre",nullable = false)
    private String genre; //体裁
    @Column(name="produce_group",nullable = false)
    private String group; //制作班底
    @Lob
    @Column(name="file_description",nullable = false)
    private String description; //影片简介
    @Lob
    @Column(name="director_state",nullable = false)
    private String state; //导演阐述
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private MovieCategory movieCategory;
    @Column(name="image_url",nullable = false)
    private String imageUrl;

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

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
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

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (!name.equals(movie.name)) return false;
        if (!firstType.equals(movie.firstType)) return false;
        if (!attribute.equals(movie.attribute)) return false;
        if (!year.equals(movie.year)) return false;
        if (!author.equals(movie.author)) return false;
        if (!genre.equals(movie.genre)) return false;
        if (!group.equals(movie.group)) return false;
        if (!description.equals(movie.description)) return false;
        if (!state.equals(movie.state)) return false;
        if (!movieCategory.equals(movie.movieCategory)) return false;
        return imageUrl.equals(movie.imageUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + firstType.hashCode();
        result = 31 * result + attribute.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + movieCategory.hashCode();
        result = 31 * result + imageUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstType='" + firstType + '\'' +
                ", attribute='" + attribute + '\'' +
                ", year='" + year + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", movieCategory=" + movieCategory +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
