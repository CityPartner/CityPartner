package com.nchhr.platform.entity;

public class PageEntity {
    private int fromIndex; //起始页数
    private int pageSize; //取多少条数据
    private int currentPage; // 当前第几页
    private boolean nextPage;

    public PageEntity(int fromIndex, int pageSize, int currentPage,boolean nextPage) {
        this.fromIndex = fromIndex;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public PageEntity(){

    }
    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "fromIndex=" + fromIndex +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                '}';
    }
}
