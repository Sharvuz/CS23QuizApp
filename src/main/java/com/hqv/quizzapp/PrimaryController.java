package com.hqv.quizzapp;

import com.hqv.utils.MyAlert;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class PrimaryController {
    public void handleQuestionManagement(ActionEvent event) {
        MyAlert.getInstance().showMsg("coming son");
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Quizz App");
//        alert.setContentText("Comming soon ...");
//        alert.showAndWait();
    }
    
    public void handlePratice(ActionEvent event) {
                MyAlert.getInstance().showMsg("coming son okkk");

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Quizz App");
//        alert.setContentText("Comming soon ...");
//        alert.showAndWait();        
    }
}
