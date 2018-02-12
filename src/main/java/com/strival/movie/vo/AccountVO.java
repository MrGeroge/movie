package com.strival.movie.vo;

import com.strival.movie.po.Account;
import com.strival.movie.po.AccountProfile;
import com.strival.movie.po.Tag;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/17.
 */
public class AccountVO {
    private Account account;
    private AccountProfileVO accountProfileVO;
    private List<Tag> tags;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountProfileVO getAccountProfileVO() {
        return accountProfileVO;
    }

    public void setAccountProfileVO(AccountProfileVO accountProfileVO) {
        this.accountProfileVO = accountProfileVO;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountVO accountVO = (AccountVO) o;

        if (!account.equals(accountVO.account)) return false;
        if (!accountProfileVO.equals(accountVO.accountProfileVO)) return false;
        return tags.equals(accountVO.tags);

    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + accountProfileVO.hashCode();
        result = 31 * result + tags.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
                "account=" + account +
                ", accountProfileVO=" + accountProfileVO +
                ", tags=" + tags +
                '}';
    }
}
