package com.strival.movie.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xinghai on 2015/11/9.
 */
@Entity
@Table(name="tb_form_apply")
public class FormApply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name="form_id",nullable = false)
    private Form form;
    @Column(name="apply_time",nullable = false)
    private Date applyTime;
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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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
    public String toString() {
        return "FormApply{" +
                "id=" + id +
                ", account=" + account +
                ", form=" + form +
                ", applyTime=" + applyTime +
                ", contentDesc='" + contentDesc + '\'' +
                ", status=" + status +
                '}';
    }
}
