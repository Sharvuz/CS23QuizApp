/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.services;

import com.hqv.pojo.Category;
import com.hqv.pojo.Choice;
import com.hqv.pojo.Question;
import com.hqv.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huynh
 */
public class QuestionServices {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        //tắt cơ chế để quy trình chèn dữ liệu cùng thành công hoặc cùng thất bại
        conn.setAutoCommit(false);

        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";

        //con của statement
        PreparedStatement stm = conn.prepareCall(sql);
        //index tính từ 1
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        //kiểm tra nếu stm dương là đã chèn câu hỏi thành công
        if (stm.executeUpdate() > 0) {
            //lay questionId tu dong tang trong SQL
            int questionId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                questionId = r.getInt(1);
            }

            //choice phải có khóa ngoại
            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";

            //duyệt các lựa chọn
            for (var c : q.getChoices()) {
                //mỗi lần prepare là 1 câu truy vấn sql mới
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                //excuteQuery: truy vấn select
                //excuteUpdate: chèn truy vấn
                stm.executeUpdate();
            }
            //lúc này mới đưa dữ liệu vào DB
            conn.commit();
        } else //phục hồi dữ liệu nếu nạp thất bại
        {
            conn.rollback();
        }
    }

    public List<Question> getQuestion() throws SQLException, Exception {

        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question"); //tra ve du lieu

        List<Question> questiions = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();

            questiions.add(q);//chua danh sach cau hoi 
        }

        return questiions;
    }

    public List<Question> getQuestion(String keyWord) throws SQLException, Exception {

        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%')");
        stm.setString(1, keyWord);

        ResultSet rs = stm.executeQuery(); //tra ve du lieu

        List<Question> questiions = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();

            questiions.add(q);//chua danh sach cau hoi 
        }

        return questiions;
    }

    public List<Question> getQuestion(int num) throws SQLException, Exception {

        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        //rand() la số lượng câu hỏi lấy ra ngẫu nhiên
        stm.setInt(1, num);

        ResultSet rs = stm.executeQuery(); //tra ve du lieu

        List<Question> questiions = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content)
                    .addAllChoice(this.getChoiceByQuestionId(id)).build();

            questiions.add(q);//chua danh sach cau hoi 
        }

        return questiions;
    }

    public List<Choice> getChoiceByQuestionId(int questionId) throws SQLException, Exception {

        //B2: thiết lập kết nối
        //tạo đối tượng nhưng không new nên biến con là tĩnh (singleton)
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn                                             //khoa ngoai
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id = ?");
        stm.setInt(1, questionId);

        ResultSet rs = stm.executeQuery(); //tra ve du lieu

        List<Choice> choices = new ArrayList<>();
        //chạy rs.next để nhảy lên dòng dữ liệu đầu tiên
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Boolean isCorrect = rs.getBoolean("is_correct");
            Choice c = new Choice(id, content, isCorrect);

            choices.add(c);//chua danh sach cau hoi 
        }

        return choices;
    }

    public boolean deleteQuestion(int id) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        //B3 xử lý truy vấn
        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, id);

        //nếu true là xóa thành công trả về
        return stm.executeUpdate() > 0;
        //xóa câu hỏi thì phải xóa luôn choices, thuộc tính CASCADE trong SQL

    }

}
