package com.mai.logisticsms.service;

import cn.hutool.core.util.IdUtil;
import com.mai.logisticsms.pojo.Logistics;
import com.mai.logisticsms.pojo.Order;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Mai
 * @Date: 2022/5/21 22:05
 */

@Service
public class OrderService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 添加订单
     *
     * @param order
     */
    public void addOrder(Order order) {
        // 订单编号根据雪花算法生成
        order.setId(IdUtil.getSnowflake(1, 1).nextIdStr());
        // 设置订单状态
        order.setStatus("已下单！");
        // 设置下单时间
        order.setOrderTime(new Date());
        // 设置发货时间
        order.setShipTime(new Date());
        // 添加订单
        mongoTemplate.insert(order, "order");

    }

    /**
     * 追加物流信息
     *
     * @param logistics
     */
    public void addLogistics(Logistics logistics) {
        // 获取操作名称
        String status = logistics.getOperation();
        // 设置操作时间
        logistics.setOperationDate(new Date());
        // 初始化Query对象，根据订单编号查询
        Query query = new Query(Criteria.where("_id").is(logistics.getOrderId()));
        // 初始化Update对象
        Update update = new Update();
        // 更新订单状态
        update.set("status", status);
        // 追加物流信息
        update.push("logistics", logistics);
        // 更新订单信息
        mongoTemplate.upsert(query, update, Order.class, "order");
    }

    public Order selectOrderById(String id) {
        // 初始化Query对象，根据订单编号查询
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Order.class, "order");
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    public Map<String, Object> selectOrderList() {
        List<Order> orderList = mongoTemplate.findAll(Order.class, "order");
        Map<String, Object> result = new HashMap<>();
        if (orderList == null || orderList.isEmpty()) {
            result.put("code", "400");
            result.put("message","没有相关订单信息");
        } else {
            result.put("code", "0");
            result.put("count", orderList.size());
            result.put("data", orderList);
        }

        return result;
    }


    /**
     * 根据订单编号删除订单记录
     *
     * @param id
     * @return
     */
    public boolean deleteOrderById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Order.class, "order");
        return result.getDeletedCount() > 0 ? true : false;
    }

}
