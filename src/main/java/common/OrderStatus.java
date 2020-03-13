package common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-13
 * Time: 9:54
 */
@Getter
@ToString
public enum OrderStatus {
    PLAYING(1,"待支付"),OK(2,"支付完成");
    private int flg;
    private String desc;

    OrderStatus(int flg,String desc) {
        this.flg = flg;
        this.desc = desc;
    }
    //浏览订单的时候，直接拿到的是一个数字，查找相应的状态
    public static OrderStatus valueOf(int flg) {
        for (OrderStatus orderStatus :OrderStatus.values()) {
            if(orderStatus.flg == flg) {
                return orderStatus;
            }
        }
        throw new RuntimeException("OrderStatus is not fount");
    }

}
