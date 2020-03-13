package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:查询goods这张表里面所有的商品
 * User: GAOBO
 * Date: 2020-02-12
 * Time: 9:48
 */
@WebServlet("/browseGoods")
public class GoodsBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoodsBrowseServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            String sql = "select id,name,introduce,stock,unit,price,discount " +
                    "from goods";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            //解析结果集里面的内容
            while (resultSet.next()) {
                Goods goods = this.extractGoods(resultSet);
                if(goods != null) {
                    list.add(goods);
                }
            }
            System.out.println("list"+list);
            //把list转换为Json ,然后发给前端 进行解析
            //可以方便的将模型对象转换为JSON
            ObjectMapper mapper = new ObjectMapper();
            //将响应包推送给浏览器
            PrintWriter pw = resp.getWriter();
            //将list转换为字符串，并将json字符串填充到PrintWriter当中
            mapper.writeValue(pw,list);
            Writer writer = resp.getWriter();
            //把流响应给前端页面
            writer.write(pw.toString());

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }
    //解析resultSet当中的商品信息，组装从一个商品的对象goods
    public Goods extractGoods(ResultSet resultSet) throws SQLException{
        Goods goods = new Goods();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setIntroduce(resultSet.getString("introduce"));
        goods.setStock(resultSet.getInt("stock"));
        goods.setUnit(resultSet.getString("unit"));
        goods.setPrice(resultSet.getInt("price"));
        goods.setDiscount(resultSet.getInt("discount"));
        return goods;
    }

}
