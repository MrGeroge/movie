package com.strival.movie.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/20.
 */
public class PageVO <T> {

    private List<T> rows =new LinkedList<>();

    private long total; //总页数

    private long currentPage;

    private boolean hasNextPage;

    private boolean hasPreviousPage;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
}

