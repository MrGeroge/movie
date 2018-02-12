package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/20.
 */
public class MovieCategoryVO {
    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCategoryVO that = (MovieCategoryVO) o;

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
        return "MovieCategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
