package com.strival.movie.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by xinghai on 2015/12/17.
 */
public class SponsorVO {
    private long id;
    private String logoUrl;
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

        SponsorVO sponsorVO = (SponsorVO) o;

        if (id != sponsorVO.id) return false;
        if (!logoUrl.equals(sponsorVO.logoUrl)) return false;
        return website.equals(sponsorVO.website);

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
        return "SponsorVO{" +
                "id=" + id +
                ", logoUrl='" + logoUrl + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
