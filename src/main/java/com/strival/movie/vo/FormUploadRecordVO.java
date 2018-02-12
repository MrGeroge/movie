package com.strival.movie.vo;

import com.strival.movie.po.Account;

import java.util.Date;

/**
 * Created by xinghai on 2015/12/27.
 */
public class FormUploadRecordVO {
    private long id;
    private Date uploadTime;
    private Account account;
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

        FormUploadRecordVO that = (FormUploadRecordVO) o;

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
        return "FormUploadRecordVO{" +
                "id=" + id +
                ", uploadTime=" + uploadTime +
                ", account=" + account +
                ", uploadUrl='" + uploadUrl + '\'' +
                '}';
    }
}
