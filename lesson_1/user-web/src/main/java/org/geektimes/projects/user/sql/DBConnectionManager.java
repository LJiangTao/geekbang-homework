package org.geektimes.projects.user.sql;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManager {

  public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE IF EXISTS users";
  public static final String CREATE_USERS_TABLE_DDL_SQL =
      "CREATE TABLE users("
          + "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
          + "name VARCHAR(16) NOT NULL, "
          + "password VARCHAR(64) NOT NULL,"
          + "phone VARCHAR(64) NOT NULL"
          + ")";
  public static final String INSERT_USER_DML_SQL =
      "INSERT INTO users(name,password,email,phoneNumber) VALUES "
          + "('A','******','a@gmail.com','1') , "
          + "('B','******','b@gmail.com','2') , "
          + "('C','******','c@gmail.com','3') , "
          + "('D','******','d@gmail.com','4') , "
          + "('E','******','e@gmail.com','5')";
  /** 数据类型与 ResultSet 方法名映射 */
  static Map<Class, String> typeMethodMappings = new HashMap<>();

  static {
    typeMethodMappings.put(Long.class, "getLong");
    typeMethodMappings.put(String.class, "getString");
  }

  private Logger logger = Logger.getLogger("DBConnectionManager");
  private Connection connection;

  public DBConnectionManager() {}

  private static String mapColumnLabel(String fieldName) {
    return fieldName;
  }

  @PostConstruct
  public void initDb() {
    String dbUrl = "jdbc:derby:/db/user-platform;create=true";
    try {
      this.connection = DriverManager.getConnection(dbUrl);
//      PreparedStatement DROP = connection.prepareStatement(DROP_USERS_TABLE_DDL_SQL);
//      DROP.execute();
//      PreparedStatement CREATE = connection.prepareStatement(CREATE_USERS_TABLE_DDL_SQL);
//      CREATE.execute();
      logger.log(Level.INFO, "DB IS READY");
    } catch (SQLException throwables) {
      logger.log(Level.SEVERE, "DbManager create Connection failure see blow stacks");
      throwables.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }

  public void releaseConnection() {
    if (this.connection != null) {
      try {
        this.connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e.getCause());
      }
    }
  }
}
