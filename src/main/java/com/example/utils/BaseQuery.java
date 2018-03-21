package com.example.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/3/21.
 */
public class BaseQuery implements Serializable {
    private Integer page;
    private Integer size;
    private String sort;
    private String orderBy;
    private Map<String, Object> params;

    public BaseQuery() {
    }

    public int getPage() {
        return this.page == null ? 1 : this.page.intValue();
    }

    public void setPage(int page) {
        this.page = Integer.valueOf(page);
    }

    public int getSize() {
        return this.size == null ? 10 : this.size.intValue();
    }

    public void setSize(int size) {
        this.size = Integer.valueOf(size);
    }

    public String getSort() {
        return this.orderBy != null && this.orderBy.length() > 0 ? this.orderBy + " " + this.sort : this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void setPage(String pageNo, int defaultValue) {
        try {
            this.page = Integer.valueOf(pageNo);
        } catch (NumberFormatException var4) {
            this.page = Integer.valueOf(defaultValue);
        }

    }

    public void setSize(String pageSize, int defaultValue) {
        try {
            this.size = Integer.valueOf(pageSize);
        } catch (NumberFormatException var4) {
            this.size = Integer.valueOf(defaultValue);
        }

    }

    public void setSort(String sort, String defaultValue) {
        if (sort != null && sort.length() > 0) {
            this.sort = sort;
        } else {
            this.sort = defaultValue;
        }

    }

    public void setOrderBy(String orderBy, String defaultValue) {
        if (orderBy != null && orderBy.length() > 0) {
            this.orderBy = orderBy;
        } else {
            this.orderBy = defaultValue;
        }

    }

    public String getPureSort() {
        return this.sort;
    }

    public String getOrderClause() {
        StringBuilder strBuilder = new StringBuilder();
        if (this.orderBy != null && this.orderBy.length() > 0) {
            strBuilder.append(this.orderBy);
            if (this.sort != null && this.sort.length() > 0) {
                strBuilder.append(" ").append(this.sort);
            }
        }

        return strBuilder.toString();
    }

    public int getOffset() {
        int pageNo = this.getPage();
        int pageSize = this.getSize();
        return (pageNo - 1) * pageSize;
    }
}
