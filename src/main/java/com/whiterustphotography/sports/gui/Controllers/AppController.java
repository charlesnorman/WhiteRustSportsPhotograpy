package com.whiterustphotography.sports.gui.Controllers;

import com.whiterustphotography.sports.gui.Models.Model;
import com.whiterustphotography.sports.gui.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    public BorderPane app_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getMenuSelectedOption().addListener(
                (_, _, newValue) -> {
                    switch (newValue) {
                        case MenuOptions.PRINT_BARCODES -> app_parent.setCenter(
                                Model.getInstance().getViewFactory().getPrintBarCodeView());
                        case MenuOptions.ORDER_ENTRY -> app_parent.setCenter(
                                Model.getInstance().getViewFactory().getOrderEntryView());
                        case MenuOptions.RENAME_FILES -> app_parent.setCenter(
                                Model.getInstance().getViewFactory().getRenameFilesView());

                    }
                });
    }
}
