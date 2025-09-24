/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.hqv.singlet;

import com.hqv.pojo.Question;
import com.hqv.utils.Configs;
import com.hqv.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author huynh
 */
public class PracticeController implements Initializable {

    @FXML
    private TextField txtNum;
    @FXML
    private Text txtContent;
    @FXML
    private Text txtResult;
    @FXML
    private VBox vboxChoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private List<Question> questions;
    private int currentQuestion;

    //lấy danh sách câu hỏi khi bấm start
    public void handleStart(ActionEvent event) throws SQLException, Exception {
        try {
            int num = Integer.parseInt(this.txtNum.getText());
            
            questions = Configs.questionServices.getQuestion(num);

            this.currentQuestion = 0;
            this.loadQuestion();
        } catch (NumberFormatException ex) {
            MyAlert.getInstance().showMsg("Vui lòng nhập số câu hỏi hợp lệ");
        }
    }

    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        
        for(int i = 0; i < q.getChoices().size(); i++) {
            if(q.getChoices().get(i).isCorrect()) {
                RadioButton r = (RadioButton)this.vboxChoice.getChildren().get(i);
                
                if(r.isSelected()) {
                    this.txtResult.setText("CHÍNH XÁC!!!");
                    this.txtResult.setStyle("-fx-fill: blue");
                } else {
                    this.txtResult.setText("ĐÁP ÁN KHÔNG CHÍNH XÁC!!!");
                    this.txtResult.setStyle("-fx-fill: red");
                }
            
            }
        }
    }

    public void handleNext(ActionEvent event) {
        if(this.currentQuestion < questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());
        
        ToggleGroup g = new ToggleGroup();
        
        //clear choices câu trước
        vboxChoice.getChildren().clear();
        
        for(var c : q.getChoices()) {
            RadioButton rd = new RadioButton(c.getContent());
            rd.setToggleGroup(g);
            
            vboxChoice.getChildren().add(rd);
        }
    }
}
