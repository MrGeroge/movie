package com.strival.movie.vo;

import java.util.Date;

/**
 * Created by xinghai on 2015/12/19.
 */
public class FormSimpleVO {
    private long id;
    private String formName;
    private String createTime;
    private String controlDesc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getControlDesc() {
        return controlDesc;
    }

    public void setControlDesc(String controlDesc) {
        this.controlDesc = controlDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormSimpleVO)) return false;

        FormSimpleVO that = (FormSimpleVO) o;

        if (id != that.id) return false;
        if (!formName.equals(that.formName)) return false;
        if (!createTime.equals(that.createTime)) return false;
        return controlDesc.equals(that.controlDesc);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + formName.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + controlDesc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FormSimpleVO{" +
                "id=" + id +
                ", formName='" + formName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", controlDesc='" + controlDesc + '\'' +
                '}';
    }
}
