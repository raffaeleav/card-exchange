package storage;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;


public class ConPool {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException{
        if (dataSource==null){
            PoolProperties p= new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/CardExchange?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("root");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);

            dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);
        }
        return dataSource.getConnection();
    }
}
