package com.mai.logisticsms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mai
 * @Date: 2022/5/21 22:04
 * @Logistics:物流
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logistics implements Serializable {

    /**
     * 订单编号
     * 操作名称
     * 操作员
     * 操作员电话
     * 操作时间
     * 操作地址
     * 详细信息
     * */

    private String orderId;
    private String operation;
    private String operator;
    private String operatorPhone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone =  "GMT+8")
    private Date operationDate;
    private String address;
    private String details;

}
