package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/12/17.
 */
@Entity
@Table(name="tb_account_tag_record")
public class AccountTagRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name="tag_id",nullable = false)
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

        AccountTagRecord that = (AccountTagRecord) o;

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
        return "AccountTagRecord{" +
                "id=" + id +
                ", account=" + account +
                ", tag=" + tag +
                '}';
    }
}
