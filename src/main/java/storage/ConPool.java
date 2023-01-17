package storage;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Classe del pool di connessioni per il database CardExchange.
 *
 * @author Michele Menzione
 * @version 1.0
 */
public class ConPool {
    /**
     * Sorgente di dati per il pool di connessioni
     */
    private static DataSource dataSource;


    /**
     * Restituisce una connessione dal pool. Se il pool non esiste, viene creato.
     * @return Una connessione al database CardExchange.
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public static Connection getConnection() throws SQLException{
        if (dataSource==null){
            PoolProperties p= new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/CardExchange?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("michelemenzione");
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
