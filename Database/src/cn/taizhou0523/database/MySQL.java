package cn.taizhou0523.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class MySQL extends Database {

    private String dbURL, username, password;

    MySQL(String host, short port, String username, String password, String dbName, String encoding)
            throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        dbURL = String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=%s",
                host, port, dbName, encoding
        );
        this.username = username;
        this.password = password;
    }

    @Override
    protected void prepare() throws SQLException {
        close();
        conn = DriverManager.getConnection(dbURL, username, password);
        conn.setAutoCommit(false);
    }

    @Override
    public int executeChange(String sql, Object... param) throws SQLException {
        prepare();
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i = 1; i <= param.length; i++)
            pst.setObject(i, param[i - 1]);
        int update = pst.executeUpdate();
        conn.commit();
        conn.close();
        return update;
    }

    @Override
    public ResultSet executeQuery(String sql, Object... param) throws SQLException {
        prepare();
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i = 1; i <= param.length; i++)
            pst.setObject(i, param[i - 1]);
        ResultSet rs = pst.executeQuery();
        conn.setAutoCommit(true);
        return rs;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed())
            conn.close();
    }
}
