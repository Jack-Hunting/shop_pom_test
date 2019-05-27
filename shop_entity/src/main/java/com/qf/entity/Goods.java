package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;

/**
 * @vervion 1.0
 * @date 2019/05/20 16:58
 * @user Jack-Hunting
 */
@Data
public class Goods implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private String gname;
    private String ginfo;
    private BigDecimal gprice;
    private String gimg;
    private int tid;
    private int gsave;
}
