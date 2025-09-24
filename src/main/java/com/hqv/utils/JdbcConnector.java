/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huynh
 */
public class JdbcConnector {
    private static JdbcConnector instance;
     
    //dùng khối tĩnh để đảm bảo driver chỉ nạp 1 lần
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final Connection conn;
    //đưa đối tượng connection về singleton    
    private JdbcConnector() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "1210");
    }    
    
//    private JdbcConnector getInstance() throws SQLException {
//        
//    }
    
    public static JdbcConnector getInstance() throws SQLException {
        if(instance == null) {
            instance = new JdbcConnector();
        }
        return instance;
    }
 
    public Connection connect() {
        return this.conn;
    }
    
    //mở không tắt là bị rò rỉ
    public void close() throws SQLException {
        if(this.conn != null) {
            this.conn.close();
        }
    }
    
}
