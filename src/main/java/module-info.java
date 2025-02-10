module com.whiterustphotography.sports {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires metadata.extractor;


    opens com.whiterustphotography.sports to javafx.fxml;
    exports com.whiterustphotography.sports;
    exports com.whiterustphotography.sports.gui.Controllers;
    exports com.whiterustphotography.sports.gui.Models;
    exports com.whiterustphotography.sports.gui.Views;

}