package com.qf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @vervion 1.0
 * @date 2019/05/22 21:10
 * @user Jack-Hunting
 */
@Data
public class Page implements Serializable {

    private int pageSize = 8;

    private int totalPage;

    private int totalCount;

    private int currentPage = 1;

    private String url;

    private List<Goods> goodsList = new ArrayList<>();
}
