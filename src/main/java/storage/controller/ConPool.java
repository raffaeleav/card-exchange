package storage.controller;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * La classe permette la connessione con il database MySQL .
 * @author Francesco Di Domenico
 */
public class ConPool {
    private static DataSource dataSource;
    /**
     * Il metodo permette di gestire la connessione con il database tramite parametro PoolProperties,dove
     * imposta i parametri per la connessione come ad esempio:Url,DriverclassName,Username e password.
     */
    public static Connection getConnection() throws SQLException{
        if (dataSource==null){
            PoolProperties p= new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/CardExchange?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("Fra23032000!");
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
