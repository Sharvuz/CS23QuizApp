<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */
public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;
    
    private MyAlert() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setHeaderText("QuizAPP ne con cho");
    }
    
    public static MyAlert getInstance() {
        if(instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }
    
    public void showMsg(String msg) {
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
    
=======
package com.hqv.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MyAlert {

    private static MyAlert instance;  // biến static để giữ instance duy nhất
    private final Alert alert;        // đối tượng Alert bên trong

    // Constructor private hoặc hạn chế tạo trực tiếp
    public MyAlert() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setHeaderText("Quiz App");  // tiêu đề chung
    }

    // Phương thức trả về duy nhất một instance
    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();  // tạo mới nếu chưa tồn tại
        }
        return instance;
    }

    // Hiển thị thông báo
    public void showMsg(String message) {
        this.alert.setContentText(message);
        this.alert.showAndWait();
    }

    public Optional<ButtonType> showMsg(String message, Alert.AlertType type) {
        this.alert.setContentText(message);
        this.alert.setAlertType(type);
        return this.alert.showAndWait();
    }
>>>>>>> 4ece615 (quiz)
}
