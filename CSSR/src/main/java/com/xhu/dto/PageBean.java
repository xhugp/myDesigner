package com.xhu.dto;

import java.util.List;

/**
 * 通用分页类
 * Created by gp on 2018/3/8.
 */

public class PageBean<T> {
    private Integer pageSize = 10;//每页数据数量,默认10
    private Integer totalSize;//总数量
    private Integer currentPage = 1;//当前页，默认第一页
    private Integer pageNumber;//总页数


    private Integer index;//查询数据库从哪条数据开始查
    private Integer size;//查询多少数据

    private List<T> datas;

    public PageBean() {
    }

    public PageBean(Integer pageSize, Integer totalSize, Integer currentPage) {
        setPageSize(pageSize);
        setTotalSize(totalSize);
        setCurrentPage(currentPage);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize!=null && pageSize > 0) {
            this.pageSize = pageSize;
            size = this.pageSize;
        }
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
        //设置总页数
        this.setPageNumber();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if(currentPage == null || currentPage < 1){
            this.currentPage = 1;
        }
        else if(currentPage > pageNumber && currentPage > 1){
            this.currentPage = pageNumber;
        }else {
            this.currentPage = currentPage;
        }
        setIndex();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber() {
        int number = 0;
        if(totalSize%pageSize == 0){
            number = totalSize/pageSize;
        }else{
            number = (totalSize/pageSize) + 1;
        }
        this.pageNumber = number;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex() {
        if(this.currentPage != null && size != null) {
            this.index = (this.currentPage - 1) * this.size;
        }
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", currentPage=" + currentPage +
                ", pageNumber=" + pageNumber +
                ", index=" + index +
                ", size=" + size +
                ", datas=" + datas +
                '}';
    }
}
