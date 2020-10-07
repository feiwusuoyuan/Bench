package com.bench.Bench.utils;

public class pages {
    //第几页
    private int page;
    //显示的数目
    private int size;
    //排序字段
    private String sortColumn;
    //排序方式
    private String direction;
    //无参构造
    public pages(){

    }
    public pages(int page) {
        this.page = page;
    }

    public pages(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public pages(int page, int size, String sortColumn) {
        this.page = page;
        this.size = size;
        this.sortColumn = sortColumn;
    }

    public pages(int page, int size, String sortColumn, String direction) {
        this.page = page;
        this.size = size;
        this.sortColumn = sortColumn;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public String getDirection() {
        return direction;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "pages{" +
                "page=" + page +
                ", size=" + size +
                ", sortColumn='" + sortColumn + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
