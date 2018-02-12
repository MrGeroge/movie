package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/19.
 */
public class MovieNameVO {
    private String name;

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

        MovieNameVO that = (MovieNameVO) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "MovieNameVO{" +
                "name='" + name + '\'' +
                '}';
    }
}
