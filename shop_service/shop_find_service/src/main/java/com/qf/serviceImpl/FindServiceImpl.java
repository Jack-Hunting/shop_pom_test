package com.qf.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.service.IFindService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @vervion 1.0
 * @date 2019/05/22 17:14
 * @user Jack-Hunting
 */
@Service
public class FindServiceImpl implements IFindService {

    @Autowired
    private SolrClient solrClient;

    /**
     * 通过关键字查询相关的商品
     * @param keyword   搜索的关键字
     * @return  返回商品集合
     */
    @Override
    public Page getGoodsByKeyword(String keyword, Page page) {
        SolrQuery solrQuery;
        if (keyword == null||"".equals(keyword)){
            solrQuery = new SolrQuery("*:*");
        }else {
            solrQuery = new SolrQuery("gname:"+keyword+"|| ginfo:"+keyword);
        }

//      设置高亮
        solrQuery.setHighlight(true);
//      设置高亮样式
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
//      设置高亮字段
        solrQuery.addHighlightField("gname");

        solrQuery.setStart((page.getCurrentPage()-1)*page.getPageSize());
        solrQuery.setRows(page.getPageSize());

        List<Goods> goodsList = new ArrayList<>();
//        执行查询
        try {
//            根据solrQuery查询条件获得查询结果
            QueryResponse response = solrClient.query(solrQuery);
//            通过queryResponse对象获得普通的查询结果
            SolrDocumentList results = response.getResults();

            int count = Integer.parseInt(results.getNumFound() + "");
//            设置总条数
            page.setTotalCount(count);
            double ceil = Math.ceil((double) count / page.getPageSize());
            page.setTotalPage((int) ceil);

//              获得高亮的集合
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
//            遍历集合并将results中的值赋给goodslist集合返还给前端展示
            for (SolrDocument result : results) {
                Goods good = new Goods();
                good.setId(Integer.valueOf(result.get("id")+""));
                good.setGname(result.get("gname")+"");
                good.setGinfo(result.get("ginfo")+"");

                BigDecimal bigDecimal = BigDecimal.valueOf((float)result.get("gprice"));
                good.setGprice(bigDecimal);

                good.setGimg(result.get("gimg")+"");
                good.setGsave(Integer.parseInt(result.get("gsave")+""));

//                判断当前字段是否存在高亮
                if (highlighting.containsKey(good.getId()+"")){
//                    获得高亮的字段
                    Map<String, List<String>> stringListMap = highlighting.get(good.getId() + "");
                    List<String> gname = stringListMap.get("gname");
                    if (gname!=null){
                        good.setGname(gname.get(0));
                    }

                }

//                将查询到的good对象存入集合
                goodsList.add(good);
            }
            page.setGoodsList(goodsList);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page;
    }


}
