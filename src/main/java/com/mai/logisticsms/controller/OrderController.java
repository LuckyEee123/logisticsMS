package com.mai.logisticsms.controller;

import com.mai.logisticsms.pojo.Logistics;
import com.mai.logisticsms.pojo.Order;
import com.mai.logisticsms.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Mai
 * @Date: 2022/5/21 22:06
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 添加订单
     * @param order
     * @return
     */
    @PostMapping("add")
    public String addOrder(Order order) {
        orderService.addOrder(order);
        return "订单添加成功";
    }

    /**
     * 更新订单信息
     * 添加物流信息
     * @param logistics
     * @return
     */
    @PostMapping("update")
    public String updateOrderAddLogistics(Logistics logistics) {
        orderService.addLogistics(logistics);
        return "物流添加成功";
    }

    /**
     * 通过订单编号查询
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Order selectOrderById(@PathVariable String id) {
        return orderService.selectOrderById(id);
    }

    /**
     * 查询所有订单
     * @return
     */
    @GetMapping("list")
    public Map<String, Object> selectOrderList() {
        return orderService.selectOrderList();
    }

    /**
     * 根据订单编号删除订单记录
     * @param id
     * @return
     */
    @PostMapping("delete")
    public String deleteOrderById(String id) {
        orderService.deleteOrderById(id);
        return "删除成功";
    }

}
