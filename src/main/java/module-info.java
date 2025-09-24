<<<<<<< HEAD
module com.hqv.quizzapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hqv.quizzapp to javafx.fxml;
    exports com.hqv.quizzapp;
=======
module com.hqv.singlet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    //requires lombok; //nho them de dung
    requires java.sql;
    opens com.hqv.singlet to javafx.fxml;
    exports com.hqv.singlet;
    exports com.hqv.pojo;
>>>>>>> 4ece615 (quiz)
}
