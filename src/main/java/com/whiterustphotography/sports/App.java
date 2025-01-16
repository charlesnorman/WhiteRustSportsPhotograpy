package com.whiterustphotography.sports;

import com.whiterustphotography.sports.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Model.getInstance().getViewFactory().showAppWindow();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
