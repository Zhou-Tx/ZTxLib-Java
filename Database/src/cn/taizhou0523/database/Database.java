package cn.taizhou0523.database;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {

    Connection conn = null;

    protected abstract void prepare() throws SQLException;

    public abstract int executeChange(String sql, Object... param) throws SQLException;

    public abstract ResultSet executeQuery(String sql, Object... param) throws SQLException;

    public abstract void close() throws SQLException;

    public static Constructor getConstructor(DatabaseDriver driver) throws NoSuchMethodException {
        Constructor constructor;
        switch (driver) {
            case MySQL:
                constructor = MySQL.class.getDeclaredConstructor(String.class,int.class,String.class,String.class,String.class,String.class);
                break;
            case MariaDB:
                constructor = MariaDB.class.getDeclaredConstructor(String.class,int.class,String.class,String.class,String.class,String.class);
                break;
            case SQLite:
                constructor = SQLite.class.getDeclaredConstructor(String.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + driver);
        }
        constructor.setAccessible(true);
        return constructor;
    }

    public enum DatabaseDriver {
        MySQL,
        MariaDB,
        SQLite
    }
}