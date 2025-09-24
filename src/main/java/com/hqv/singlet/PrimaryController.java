package com.hqv.singlet;

import com.hqv.utils.MyAlert;
import com.hqv.utils.MyStage;
import com.hqv.utils.theme.DarkFactory;
import com.hqv.utils.theme.DefaultFactory;
import com.hqv.utils.theme.LightFactory;
import com.hqv.utils.theme.Theme;
import com.hqv.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{
    @FXML private ComboBox<Theme> cbThemes;
    //phải ghi đè phương thức sẽ chạy khi sân khấu vừa nạp theme lên 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nạp toàn bộ theme lên
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void changeTheme(ActionEvent event) {
        //abtracts factory
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
        
//        switch (this.cbThemes.getSelectionModel().getSelectedItem()) {
//            case DEFAULT:
//                ThemeManager.setThemeFactory(new DefaultFactory());
//                ThemeManager.applyTheme(this.cbThemes.getScene());
////                this.cbThemes.getScene().getRoot().getStylesheets().clear();
////                //đường dẫn tuyệt đối
////                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("style.css").toExternalForm());
//                break;
//            case DARK:
//                ThemeManager.setThemeFactory(new DarkFactory());
//                ThemeManager.applyTheme(this.cbThemes.getScene());
////                this.cbThemes.getScene().getRoot().getStylesheets().clear();
////                //đường dẫn tuyệt đối
////                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("dark.css").toExternalForm());
//                break;
//            case LIGHT:
//                ThemeManager.setThemeFactory(new LightFactory());
//                ThemeManager.applyTheme(this.cbThemes.getScene());
////                this.cbThemes.getScene().getRoot().getStylesheets().clear();
////                //đường dẫn tuyệt đối
////                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("light.css").toExternalForm());
//                break;
//        }
    }
    
    //=============singleton============
    public void handleQuestionManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("questionsManagement.fxml");
    }

    public void handlePractice(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("practice.fxml");
    }
}
