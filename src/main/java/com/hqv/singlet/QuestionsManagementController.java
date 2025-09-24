/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.hqv.singlet;

import com.hqv.pojo.Category;
import com.hqv.pojo.Choice;
import com.hqv.pojo.Level;
import com.hqv.pojo.Question;
import com.hqv.utils.Configs;
import com.hqv.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author huynh
 */
//interface hiện thực các thành viên
public class QuestionsManagementController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates; // them fxml de lien ket, nhưng chưa hiện chuỗi của đối tượng nên phải dùng toString để hiển thị
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private VBox vboxChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggleChoice;
    @FXML
    private TableView<Question> tbQuestions; // mỗi dòng là 1 question
    @FXML
    private TextField txtSearch;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //push danh sách cates lên FXML
            this.cbCates.setItems(FXCollections.observableList(Configs.cateServices.getCate()));
            //push danh sách levels lên FXML
            this.cbLevels.setItems(FXCollections.observableList(Configs.levelServices.getLevels()));
            //push danh sách table question lên FXML
            this.loadColumns();
            try {
                this.tbQuestions.setItems(FXCollections.observableList(Configs.questionServices.getQuestion()));
            } catch (Exception ex) {
                Logger.getLogger(QuestionsManagementController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
            
        //ngoài getter và setter thì còn phương thức property
        this.txtSearch.textProperty().addListener(e -> {
            try {
                //trước khi nạp dữ liệu tìm kiếm thì clear để tránh bị nạp chồng dữ liệu
                this.tbQuestions.getItems().clear();
                //nơi sẽ gọi khi thông báo thay đổi
                this.tbQuestions.setItems(FXCollections.observableList(Configs.questionServices.getQuestion(this.txtSearch.getText())));
            } catch (Exception ex) {
                Logger.getLogger(QuestionsManagementController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
    }

    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        //thêm class của chính nó
        h.getStyleClass().add("main");

        RadioButton r = new RadioButton();
        //set togglegroup
        r.setToggleGroup(toggleChoice);

        TextField txt = new TextField();

        //quy luật là get rồi mới add
        h.getChildren().addAll(r, txt);

        this.vboxChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());

            //duyệt từng con của vBox là hBox
            for (var c : this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;

                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }

            //gom dữ liệu vào question
            Configs.questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại!");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ!");
        }
    }
    
    

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);
        
        
        //thêm nút xóa cho câu ql câu hỏi
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(c -> {
            TableCell cell = new TableCell();
            
            Button btn = new Button("Xóa");
            //xử lý nút xóa
            btn.setOnAction(e -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("Xóa câu hỏi thì các đáp án cũng sẽ bị xóa theo. Bạn có chắc chắn muốn xóa không?"
                        , Alert.AlertType.CONFIRMATION);
                
                //xét xem có đồng ý xóa không
                if(t.isPresent() && t.get().equals(ButtonType.OK)) {
                    Question q = (Question)cell.getTableRow().getItem();
                    try {
                        if(Configs.questionServices.deleteQuestion(q.getId()) == true) {
                            //xóa khỏi FXML cho đồng bộ
                            this.tbQuestions.getItems().remove(q);
                            MyAlert.getInstance().showMsg("Xóa câu hỏi thành công!", Alert.AlertType.INFORMATION);
                        } else {
                            MyAlert.getInstance().showMsg("Xóa câu hỏi thất bại!", Alert.AlertType.WARNING);
                        }
                    } catch (SQLException ex) {
                            MyAlert.getInstance().showMsg("Hệ thống lỗi, Lý do: ", Alert.AlertType.ERROR);
                    }
                }
            });
            
            cell.setGraphic(btn);
            
            return cell;
        });
        
        this.tbQuestions.getColumns().addAll(colId, colContent, colAction);

    }
}
