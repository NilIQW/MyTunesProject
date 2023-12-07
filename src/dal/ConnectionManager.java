package dal;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private final SQLServerDataSource dataSource;

    public ConnectionManager() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.34");
        dataSource.setDatabaseName("MyTunesNT");
        dataSource.setUser("CSe2023b_e_21");
        dataSource.setPassword("CSe2023bE21#23");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);

    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        ConnectionManager databaseConnection = new ConnectionManager();
        Connection connection = databaseConnection.getConnection();
        System.out.println("Yaaay!");
        connection.close();


    }
}