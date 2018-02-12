package com.strival.movie.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xinghai on 2015/12/27.
 */
@Entity
@Table(name="tb_form_upload_record")
public class FormUploadRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="upload_time",nullable = false)
    private Date uploadTime;
    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account account;
    @Column(name="upload_url",nullable = false)
    private String uploadUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormUploadRecord that = (FormUploadRecord) o;

        if (id != that.id) return false;
        if (!uploadTime.equals(that.uploadTime)) return false;
        if (!account.equals(that.account)) return false;
        return uploadUrl.equals(that.uploadUrl);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + uploadTime.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + uploadUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FormUploadRecord{" +
                "id=" + id +
                ", uploadTime=" + uploadTime +
                ", account=" + account +
                ", uploadUrl='" + uploadUrl + '\'' +
                '}';
    }
}
