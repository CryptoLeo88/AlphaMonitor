package com.block.utils;

import java.util.Map;

public class PageUtil {
    private Integer page;
    private Integer size;
    private Map<String,String> search;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Map<String, String> getSearch() {
        return search;
    }

    public void setSearch(Map<String, String> search) {
        this.search = search;
    }
}
