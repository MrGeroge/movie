package com.strival.movie.vo;

import com.strival.movie.po.Account;
import com.strival.movie.po.Tag;

/**
 * Created by xinghai on 2015/12/22.
 */
public class AccountTagRecordVO {
    private long id;
    private Account account;
    private Tag tag;

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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountTagRecordVO that = (AccountTagRecordVO) o;

        if (id != that.id) return false;
        if (!account.equals(that.account)) return false;
        return tag.equals(that.tag);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + account.hashCode();
        result = 31 * result + tag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AccountTagRecordVO{" +
                "id=" + id +
                ", account=" + account +
                ", tag=" + tag +
                '}';
    }
}
