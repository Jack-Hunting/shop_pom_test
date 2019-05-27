package com.qf.service;

import com.qf.entity.Goods;
import com.qf.entity.Page;

import java.util.List;

public interface IFindService {

    Page getGoodsByKeyword(String keyword, Page page);

}
