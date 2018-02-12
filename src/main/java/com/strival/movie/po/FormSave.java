package com.strival.movie.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xinghai on 2015/12/17.
 */
@Entity
@Table(name="tb_form_save")
public class FormSave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name="form_id",nullable = false)
    private Form form;
    @Column(name="save_time",nullable = false)
    private Date saveTime;
    @Lob
    @Column(name="content_desc",nullable = false)
    private String contentDesc;
    @Column(name="status",nullable = false)
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormSave formSave = (FormSave) o;

        if (id != formSave.id) return false;
        if (status != formSave.status) return false;
        if (!account.equals(formSave.account)) return false;
        if (!form.equals(formSave.form)) return false;
        if (!saveTime.equals(formSave.saveTime)) return false;
        return contentDesc.equals(formSave.contentDesc);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + account.hashCode();
        result = 31 * result + form.hashCode();
        result = 31 * result + saveTime.hashCode();
        result = 31 * result + contentDesc.hashCode();
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "FormSave{" +
                "id=" + id +
                ", account=" + account +
                ", form=" + form +
                ", saveTime=" + saveTime +
                ", contentDesc='" + contentDesc + '\'' +
                ", status=" + status +
                '}';
    }
}
