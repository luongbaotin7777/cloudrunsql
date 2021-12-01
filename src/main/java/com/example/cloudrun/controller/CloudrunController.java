package com.example.cloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/get")
public class CloudrunController {
//        private static String DB_URL = "jdbc:mysql://35.200.43.91:3306/democloud?Unicode=true&characterEncoding=utf-8&user=root&password=tinpro123";
    private static String DB_URL = "jdbc:mysql://google/democloud?socketFactory=com.google.cloud.sql.mysql.SocketFactory&cloudSqlInstance=stellar-arcadia-332908:asia-northeast1:demo-sosmysql&user=root&password=tinpro123";
    private Connection connection = null;

    @GetMapping("/order-items")
    public List<Map<String, Object>> getAll() {

        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            Map<String, Object> resulHashmap = new HashMap<>();
            // connnect to database 'testdb'
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL);
            // crate statement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from order_items");
            // get data from table 'student'
            ResultSet rs = preparedStatement.executeQuery();
            // show data
            while (rs.next()) {
                resulHashmap.put("id", rs.getLong("id"));
                resulHashmap.put("description", rs.getString("description"));
                resulHashmap.put("quantity", rs.getLong("quantity"));

                resultList.add(resulHashmap);
            }
            // close connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultList;
    }
}
