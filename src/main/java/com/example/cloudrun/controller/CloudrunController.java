package com.example.cloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/get")
public class CloudrunController {
//    private static String DB_URL = "jdbc:mysql://35.200.43.91:3306/democloud?Unicode=true&characterEncoding=utf-8";
    private static String DB_URL = "jdbc:mysql://google/democloud?socketFactory=com.google.cloud.sql.mysql.SocketFactory&cloudSqlInstance=stellar-arcadia-332908:asia-northeast1:demo-sosmysql&user=root&password=tinpro123";

    private static String USER_NAME = "root";
    private static String PASSWORD = "tinpro123";

    @GetMapping("/order-items")
    public List<Map<String, Object>> getAll() {

        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            Map<String, Object> resulHashmap = new HashMap<>();
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from order_items");
            // show data
            while (rs.next()) {
                resulHashmap.put("id", rs.getLong((1)));
                resulHashmap.put("order_id", rs.getLong((2)));
                resulHashmap.put("description", rs.getString((3)));
                resulHashmap.put("quantity", rs.getLong((4)));

                resultList.add(resulHashmap);
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultList;
    }

    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
