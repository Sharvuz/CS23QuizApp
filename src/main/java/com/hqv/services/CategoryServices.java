/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.services;

import com.hqv.pojo.Category;
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

//tái sử dụng lớp này
public class CategoryServices {

    public List<Category> getCate() throws SQLException {
        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category"); //tra ve du lieu

        List<Category> cates = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category c = new Category(id, name);
            cates.add(c);//chua danh sach danh muc  
            //System.out.printf("%d - %s\n", id, name);
        }

        return cates;
    }
}
