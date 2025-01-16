package com.whiterustphotography.sports.Controllers;

import com.whiterustphotography.sports.Models.Model;
import com.whiterustphotography.sports.Views.MenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button order_entry_menu_btn;
    public Button rename_files_menu_btn;
    public Button view_orders_menu_btn;
    public Button organizations_menu_btn;
    public Button add_ons_menu_btn;
    public Button packages_menu_btn;
    public Button print_barcodes_menu_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners(){

        print_barcodes_menu_btn.setOnAction(event -> onPrintBarCodes());
        order_entry_menu_btn.setOnAction(event -> onOrderEntry());
        rename_files_menu_btn.setOnAction(event -> onRenameFiles());
    }

    private void onPrintBarCodes(){
        Model.getInstance().getViewFactory().getMenuSelectedOption().set(MenuOptions.PRINT_BARCODES);
    }
    private void onOrderEntry(){
        Model.getInstance().getViewFactory().getMenuSelectedOption().set(MenuOptions.ORDER_ENTRY);
    }
    private void onRenameFiles(){
        Model.getInstance().getViewFactory().getMenuSelectedOption().set(MenuOptions.RENAME_FILES);
    }


}
