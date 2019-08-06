package com.artfactory.project01.todayart.model;

import java.io.Serializable;
import java.util.Collection;

public class ResultItems <T extends Object> implements Serializable {


    private Collection<T> items;


    public ResultItems(Collection<T> items) {
        this.items = items;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }




}
