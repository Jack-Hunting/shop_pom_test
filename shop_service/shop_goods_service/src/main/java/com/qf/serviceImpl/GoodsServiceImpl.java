package com.qf.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IFindService;
import com.qf.service.IGoodsService;
import com.qf.util.HttpUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @vervion 1.0
 * @date 2019/05/20 17:12
 * @user Jack-Hunting
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Reference
    private IFindService findService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Goods> queryList() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        return goodsList;
    }

    @Override
    public int add(Goods goods) {
        goodsMapper.insert(goods);
//        findService.insert(goods);
//        HttpUtil.sendGet("http://localhost:8083/item/createhtml?gid="+goods.getId());
//          将商品信息放入异步化队列中处理
        rabbitTemplate.convertAndSend("goods_exchange","",goods);
        return 1;
    }

    @Override
    public int delete(Integer gid) {
        return goodsMapper.deleteById(gid);
    }

    @Override
    public Goods getInfoById(Integer gid) {
        return goodsMapper.selectById(gid);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.updateById(goods);
    }
}
