package com.whiterustphotography.sports.Views;

import com.whiterustphotography.sports.Controllers.AppController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private final ObjectProperty<MenuOptions> menuSelectedOption;

    private AnchorPane printBarCodeView;
    private AnchorPane orderEntryView;
    private AnchorPane homeView;
    private AnchorPane menuView;
    private AnchorPane renameFilesView;

    public ViewFactory() {
        this.menuSelectedOption = new SimpleObjectProperty<>();
    }

    public ObjectProperty<MenuOptions> getMenuSelectedOption() {
        return menuSelectedOption;
    }

    public AnchorPane getPrintBarCodeView() {
        if(printBarCodeView == null) {
            try{
                printBarCodeView = new FXMLLoader(getClass().getResource("/Fxml/PrintBarcodes.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return printBarCodeView;
    }

    public AnchorPane getHomeView() {
        if(homeView == null) {
            try{
                homeView = new FXMLLoader(getClass().getResource("/Fxml/Home.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return homeView;
    }

    public AnchorPane getMenuView() {
        if(menuView == null) {
            try {
                menuView = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return menuView;
    }

    public AnchorPane getOrderEntryView() {
        if(orderEntryView == null) {
            try{
                orderEntryView = new FXMLLoader(getClass().getResource("/Fxml/OrderEntry.fxml")).load();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return orderEntryView;
    }

    public AnchorPane getRenameFilesView() {
        if(renameFilesView == null) {
            try{
                renameFilesView = new FXMLLoader(getClass().getResource("/Fxml/RenameFile.fxml")).load();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return renameFilesView;
    }

    public void showAppWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/App.fxml"));
        AppController appController = new AppController();
        loader.setController(appController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("White Rust Sports Photography");
        stage.setResizable(false);
        stage.show();
    }
}
