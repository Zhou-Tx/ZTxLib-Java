package cn.taizhou0523.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

    Connection conn = null;

    protected abstract void prepare() throws SQLException;

    public abstract int executeChange(String sql, Object... param) throws SQLException;

    public abstract ResultSet executeQuery(String sql, Object... param) throws SQLException;

    public abstract void close() throws SQLException;

    public static Database getMySQLInstance(String host, short port, String username, String password, String dbName, String encoding)
            throws ClassNotFoundException {
        return new MySQL(host, port, username, password, dbName, encoding);
    }

    public static Database getMariaDBInstance(String host, short port, String username, String password, String dbName, String encoding)
            throws ClassNotFoundException {
        return new MariaDB(host, port, username, password, dbName, encoding);
    }

    public static Database getSQLiteInstance(String url)
            throws ClassNotFoundException {
        return new SQLite(url);
    }
}
