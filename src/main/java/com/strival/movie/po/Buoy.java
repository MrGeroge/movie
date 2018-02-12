package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/19.
 */
@Entity
@Table(name="tb_buoy")
public class Buoy {  //左侧链接
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="logo_url",nullable = false)
    private String logoUrl;
    @Column(name="website",nullable = false)
    private String website;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buoy buoy = (Buoy) o;

        if (id != buoy.id) return false;
        if (!logoUrl.equals(buoy.logoUrl)) return false;
        return website.equals(buoy.website);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + logoUrl.hashCode();
        result = 31 * result + website.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Buoy{" +
                "id=" + id +
                ", logoUrl='" + logoUrl + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
