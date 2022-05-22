package com.mai.logisticsms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mai
 * @Date: 2022/5/21 22:04
 * @Order:订单
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    /**
     * 订单编号
     * 订单状态
     * 下单时间
     * 发货人
     * 发货人地址
     * 发货人电话
     * 发货时间
     * 收货人
     * 收货人地址
     * 收货人电话
     * 物流信息
     * */
    private String id;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone =  "GMT+8")
    private Date orderTime;
    private String shipper;
    private String shipperAddress;
    private String shipperPhone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone =  "GMT+8")
    private Date shipTime;
    private String receiver;
    private String receiverAddress;
    private String receiverPhone;
    private List<Logistics> logistics;


}
