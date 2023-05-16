import java.sql.*;

public class MySQLDemo {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // "jdbc:mysql://localhost:3306/数据库名字?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // 数据库用户名与密码
    static final String USER = "root";
    static final String PASS = "030012";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            //stmt.executeUpdate("INSERT into websites values (9,'', 'www.lpl.com',778,'cs')");   //增加行
            //stmt.executeUpdate("UPDATE websites SET name = 'uun' WHERE id = 8");                //修改
            //stmt.executeUpdate("DELETE FROM websites WHERE id = 9");                            //删除
            ResultSet rs = stmt.executeQuery("SELECT id, name, url FROM websites");             //查询特定列，结果储存至rs

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}