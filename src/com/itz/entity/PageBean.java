package com.itz.entity;

import java.util.List;

/**
 *  自定义的存储分页数据的对象 定义出5个数据
 *  private List<Permission> list; 只能对权限数据进行分页
 *  需要你可以对任何数据都能分页, 用户,商品..
 *  使用泛型技术
 *
 *  new PageBean<Permission>
 */
public class PageBean <T>{
    //定义当前的页数
    private int currentPage;

    //定义共有多少页
    private int totalPage;

    //定义共有多少条数据
    private long totalCount;

    //定义每页显示多少条数据
    private int pageSize;

    //定义List集合,存储要展示数据
    private List<T> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
