package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePool {

  public static HikariDataSource getHikariDataSource(){
    String jdbcUrl="jdbc:mysql://127.0.0.1:3306/school";
    String className="com.mysql.jdbc.Driver";
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("123456");
    hikariConfig.setDriverClassName(className);
    hikariConfig.setJdbcUrl(jdbcUrl);

    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

    return hikariDataSource;
  }

//  public static void main(String[] args){
//    getHikariDataSource();
//  }
}
