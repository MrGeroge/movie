package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_investor")
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="investor_name",nullable = false)
    private String name;
    @Column(name="investor_introduction",nullable = false)
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

        Investor investor = (Investor) o;

        if (id != investor.id) return false;
        if (!name.equals(investor.name)) return false;
        return introduction.equals(investor.introduction);

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
        return "Investor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
