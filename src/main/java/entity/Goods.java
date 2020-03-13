package entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-12
 * Time: 9:06
 */
@Data
public class Goods {
    private Integer id;
    private String name;
    private String introduce;
    private Integer stock;
    private String unit;//单位
    private Integer price;//存入数据库为整数
    private Integer discount;//88  0.88

    private Integer buyGoodsNum;//记录需要购买当前商品的数量

    public double getPrice() {
        return price * 1.0 / 100;
    }
    public int getPriceInt() {
        return price;
    }
}
