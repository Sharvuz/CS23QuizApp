/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.services;

import com.hqv.pojo.Category;
import com.hqv.pojo.Level;
import com.hqv.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huynh
 */
public class LevelServices {
    public List<Level> getLevels() throws SQLException {
        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM level"); //tra ve du lieu

        List<Level> levels = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");

            Level l = new Level(id, name, note);
            levels.add(l);//chua danh sach danh muc  
            //System.out.printf("%d - %s\n", id, name);
        }

        return levels;
    }
}
