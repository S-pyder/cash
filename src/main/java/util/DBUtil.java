package util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-11
 * Time: 10:30
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/cash";

    private static  final String USERNAME = "root";

    private static final String PASSWORD = "root";

    private static volatile DataSource DATASOURCE;

    /**
     * 获取数据库连接池：
     * @return
     */
    private static DataSource getDATASOURCE() {
        if(DATASOURCE == null) {
            synchronized (DBUtil.class) {
                if(DATASOURCE == null) {
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource)DATASOURCE).setUrl(URL);
                    ((MysqlDataSource)DATASOURCE).setUser(USERNAME);
                    ((MysqlDataSource)DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }
    //项目当中可能会出现事务的回滚
    public static Connection getConnection(boolean autoCommit) {
        try {
            Connection connection = getDATASOURCE().getConnection();
            connection.setAutoCommit(autoCommit);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取连接失败");
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement,
                             ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection != null) {
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
