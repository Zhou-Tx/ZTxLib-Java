package cn.taizhou0523.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SQLite extends Database {

    private String url;

    SQLite(String url) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.url = url;
    }

    @Override
    protected void prepare() throws SQLException {
        close();
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);
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
