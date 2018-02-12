package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/20.
 */
@Entity
@Table(name="tb_movie_category")
public class MovieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="movie_category_name",nullable = false,unique = true)
    private String name;//影片分类

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCategory that = (MovieCategory) o;

        if (id != that.id) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MovieCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
