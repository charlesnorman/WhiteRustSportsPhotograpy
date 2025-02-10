package com.whiterustphotography.sports.gui.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderEntryController implements Initializable {
    public TextField tag_fld;
    public TextField barcode_fld;
    public Button generate_barcode_btn;
    public TextField athlete_fname_fld;
    public TextField athlete_lname_fld;
    public TextField parent_fname_fld;
    public TextField parent_lname_fld;
    public TextField email_address_fld;
    public TextField team_name_fld;
    public TextField jersey_number_fld;
    public TextField phone_number_fld;
    public ChoiceBox package_llist_box;
    public TextArea package_text_area;
    public TextField package_price_fld;
    public ListView add_ons_list_view;
    public TextField package_total_fld;
    public TextField add_ons_fld;
    public TextField grand_total_fld;
    public Button save_btn;
    public Button clear_btn;
    public Button load_order_form_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
