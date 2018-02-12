package com.strival.movie.po;

import javax.persistence.*;

/**
 * Created by xinghai on 2015/11/9.
 */
@Entity
@Table(name="tb_mail_subscribe")
public class MailSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="token",nullable = false)
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MailSubscribe that = (MailSubscribe) o;

        if (id != that.id) return false;
        if (!email.equals(that.email)) return false;
        return token.equals(that.token);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + email.hashCode();
        result = 31 * result + token.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MailSubscribe{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
