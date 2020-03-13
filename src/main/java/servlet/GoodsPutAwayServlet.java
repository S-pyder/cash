package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-12
 * Time: 9:05
 */
@WebServlet("/inbound")
public class GoodsPutAwayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String introduce = req.getParameter("introduce");
        String stock = req.getParameter("stock");
        String unit = req.getParameter("unit");

        String price = req.getParameter("price");//"12.34"
        double doublePrice = Double.valueOf(price);//12.34
        int realPrice = new Double(doublePrice*100).intValue();//1234
        String discount = req.getParameter("discount");
         /*
        System.out.println("name"+name);
        System.out.println("introduce"+introduce);
        System.out.println("stock"+stock);
        System.out.println("unit"+unit);
        System.out.println("realPrice"+realPrice);
        System.out.println("discount"+discount);*/

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "insert into goods(name,introduce,stock,unit,price,discount)" +
                    " values(?,?,?,?,?,?)";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,introduce);
            preparedStatement.setInt(3,Integer.valueOf(stock));
            preparedStatement.setString(4,unit);
            preparedStatement.setInt(5,realPrice);
            preparedStatement.setInt(6,Integer.valueOf(discount));
            int ret = preparedStatement.executeUpdate();
            if(ret == 1) {
                resp.sendRedirect("index.html");//暂且这样写，浏览写完。
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement,null);
        }

    }
}
