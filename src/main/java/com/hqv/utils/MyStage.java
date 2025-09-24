/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hqv.utils;

import com.hqv.singlet.App;
import com.hqv.utils.theme.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author huynh
 */
public class MyStage {

    private static MyStage instance;
    private final Stage stage;//chỉ cần 1 sân khấu nên cho hằng số
    private static Scene scene;// chỉ cần show 1 lần nên chỉ diễn 1 lần
    
    private MyStage() {
        this.stage = new Stage();
        this.stage.setTitle("Quiz App");
    }
    
    public static MyStage getInstance() {
        if (instance == null) {
            instance = new MyStage();
        }
        return instance;
    }
    
    public void showStage(String fxml) throws IOException {
        //nếu đang ko show mới làm cho ko bị chớp giật
        //if(!this.stage.isShowing()) {
            if (scene == null) {
                scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
            }
            else {
                //thay cảnh diễn
                scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
            }
            
            
            //tất cả giao diện nhất quán 1 theme
            ThemeManager.applyTheme(scene);
            
            //lấy sân khấu hiện ra
            this.stage.setScene(scene);
            this.stage.show();
        }
  //  }
}
