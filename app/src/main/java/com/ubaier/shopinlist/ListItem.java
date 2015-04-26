package com.ubaier.shopinlist;

/**
 * Created by Ubaier on 25/04/2015.
 */
public class ListItem {

    private long id;
    private String name;
    private Boolean isDone;
    private Boolean isDeleted;

    public ListItem(long id, String name, Boolean isDone, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ListItem(String name) {
        this.name = name;
        this.isDone = false;
        this.isDeleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDone=" + isDone +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
