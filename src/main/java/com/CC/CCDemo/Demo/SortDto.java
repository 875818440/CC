package com.CC.CCDemo.Demo;

public class SortDto {
    //排序方式
    private String orderType;

    //排序字段
    private String orderField;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
    //默认为DESC排序
    public SortDto(String orderField) {
        this.orderField = orderField;
        this.orderType = "desc";
    }
    //默认为DESC排序
    public SortDto(String orderType,String orderField) {
        this.orderField = orderField;
        this.orderType = orderType;
    }
    }
