package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/19.
 */
public class InvestorVO {
    private long id;
    private String name;
    private String introduction;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvestorVO that = (InvestorVO) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return introduction.equals(that.introduction);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + introduction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "InvestorVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
