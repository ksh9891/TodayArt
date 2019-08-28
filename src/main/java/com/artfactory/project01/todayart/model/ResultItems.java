package com.artfactory.project01.todayart.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public class ResultItems<T extends Object> implements Serializable {

    private Collection<T> items;

    private int page;

    private int size;

    private long totalCount;

    private Optional boardName;

    public ResultItems(Collection<T> items, int page, int size, long totalCount) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

    }

    public ResultItems(Collection<T> items, int page, int size, long totalCount, Optional boardName) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.boardName = boardName;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public Optional getBoardName() {
        return boardName;
    }

    public void setBoardName(Optional boardName) {
        this.boardName = boardName;
    }


}
