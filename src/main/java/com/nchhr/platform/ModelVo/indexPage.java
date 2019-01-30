package com.nchhr.platform.ModelVo;

import com.nchhr.platform.entity.PageEntity;

import java.util.List;

public class indexPage<T> {

    private List<T> indexpages;
    private PageEntity pageEntity;

    public List<T> getIndexpages() {
        return indexpages;
    }

    public void setIndexpages(List<T> indexpages) {
        this.indexpages = indexpages;
    }

    public PageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    @Override
    public String toString() {
        return "indexPage{" +
                "indexpages=" + indexpages +
                ", pageEntity=" + pageEntity +
                '}';
    }
}
