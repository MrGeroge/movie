package com.strival.movie.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xinghai on 2015/11/9.
 */
@Entity
@Table(name="tb_form")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name",nullable = false,unique = true)
    private String formName;
    @Column(name="create_at",nullable = false)
    private Date createAt;
    @Lob
    @Column(name="control_desc",nullable = false)
    private String controlDesc;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

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

    public String getControlDesc() {
        return controlDesc;
    }

    public void setControlDesc(String controlDesc) {
        this.controlDesc = controlDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        if (id != form.id) return false;
        if (!formName.equals(form.formName)) return false;
        if (!createAt.equals(form.createAt)) return false;
        return controlDesc.equals(form.controlDesc);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + formName.hashCode();
        result = 31 * result + createAt.hashCode();
        result = 31 * result + controlDesc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", formName='" + formName + '\'' +
                ", createAt=" + createAt +
                ", controlDesc='" + controlDesc + '\'' +
                '}';
    }
}
