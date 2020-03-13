package entity;

import common.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-13
 * Time: 9:52
 */
@Data
public class Order {
    private String id;
    private Integer account_id;
    private String account_name;
    private String create_time;
    private String finish_time;
    private Integer actual_amount;
    private Integer total_money;
    private OrderStatus order_status;

    public OrderStatus getOrder_statusDesc() {
        return order_status;
    }
    //为了浏览订单使用
    public String getOrder_status() {
        return order_status.getDesc();
    }

    //订单项的内容也需要存储到当前订单内
    public List<OrderItem> orderItemList = new ArrayList<>();

    public double getTotal_money() {
        return total_money * 1.0 / 100;
    }
    public int getTotalMoneyInt() {
        return total_money;
    }
    //浏览订单会用到
    public double getActual_amount() {
        return actual_amount * 1.0/100;
    }
    public int getActualAmountInt() {
        return actual_amount;
    }

    //优惠
    public double getDiscount() {
        //return (this.getTotal_money() - this.getActual_amount());
        return (this.getTotalMoneyInt() - this.getActualAmountInt())*1.00 / 100;
    }
}
