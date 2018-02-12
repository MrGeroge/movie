package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/11/9.
 */
@Entity
@Table(name="tb_account_profile")
public class AccountProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="identity",nullable = true)
    private String identity;
    @Column(name="sex",nullable = true)
    private String sex;
    @Column(name="birthday",nullable = true)
    private String birthday;
    @Column(name="address",nullable = true)
    private String address;
    @Column(name="phone",nullable = true)
    private String phone;
    @OneToOne
    @JoinColumn(name="uid",nullable = false)
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountProfile that = (AccountProfile) o;

        if (id != that.id) return false;
        if (!identity.equals(that.identity)) return false;
        if (!sex.equals(that.sex)) return false;
        if (!birthday.equals(that.birthday)) return false;
        if (!address.equals(that.address)) return false;
        if (!phone.equals(that.phone)) return false;
        return account.equals(that.account);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + identity.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + account.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AccountProfile{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", account=" + account +
                '}';
    }
}
