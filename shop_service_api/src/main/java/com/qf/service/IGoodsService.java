package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsService {

    List<Goods> queryList();

    int add(Goods goods);

    int delete(Integer gid);

    Goods getInfoById(Integer gid);

    int update(Goods goods);

}
