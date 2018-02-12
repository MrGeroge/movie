package com.strival.movie.vo;

/**
 * Created by xinghai on 2015/12/19.
 */
public class MapLightVO {
    private long id;
    private String province;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapLightVO that = (MapLightVO) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        return province.equals(that.province);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + province.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MapLightVO{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", status=" + status +
                '}';
    }
}
