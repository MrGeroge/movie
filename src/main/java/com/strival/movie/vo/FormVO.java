package com.strival.movie.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by xinghai on 2015/12/17.
 */
public class FormVO {
    private long id;
    private String formName;
    private String createTime;
    private String controlDesc;
    private int status;
    private String contentDesc;
    private String username;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormVO)) return false;

        FormVO formVO = (FormVO) o;

        if (id != formVO.id) return false;
        if (status != formVO.status) return false;
        if (!formName.equals(formVO.formName)) return false;
        if (!createTime.equals(formVO.createTime)) return false;
        if (!controlDesc.equals(formVO.controlDesc)) return false;
        if (!contentDesc.equals(formVO.contentDesc)) return false;
        return username.equals(formVO.username);

    }

    @Override
    public String toString() {
        return "FormVO{" +
                "id=" + id +
                ", formName='" + formName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", controlDesc='" + controlDesc + '\'' +
                ", status=" + status +
                ", contentDesc='" + contentDesc + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

