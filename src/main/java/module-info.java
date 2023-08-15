module com.achrafsoltani.javafx.geometry2d.javafx2d {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.achrafsoltani.javafx.geometry2d.javafx2d to javafx.fxml;
    exports com.achrafsoltani.javafx.geometry2d.javafx2d;
}
