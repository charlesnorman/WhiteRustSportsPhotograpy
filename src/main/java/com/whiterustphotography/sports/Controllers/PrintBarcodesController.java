package com.whiterustphotography.sports.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javax.print.PrintService;
import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.io.InputStream;

public class PrintBarcodesController implements Initializable {
    public TextField num_barcodes_to_print;
    public Button print_barcodes_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        print_barcodes_btn.setOnAction(_ -> printBarCodes());
    }

    public void printBarCodes() {
        if(num_barcodes_to_print.getText().isEmpty()) {
            num_barcodes_to_print.setText("0");
        }
        int numberToPrint = Integer.parseInt(num_barcodes_to_print.getText());
        while(numberToPrint > 0) {
            long randomNumber = System.currentTimeMillis();
            print(("*" + Long.toString(randomNumber).substring(5) + "*"),
                    Long.toString(randomNumber).substring(5));
            numberToPrint -= 1;
        }
        /*Reset the number to print text fld*/
        num_barcodes_to_print.setText("");
    }

    public int getPageNumbers() {
        return 1;
    }

    public static final String PRINTER_NAME = "DYMO LabelWriter";
    //public static final String PRINTER_NAME = "PDF";

    PrinterJob printerJob = PrinterJob.getPrinterJob();
    PageFormat pageFormat = printerJob.defaultPage();
    Paper paper = new Paper();

    private void print(String barcode, String barcodeText) {
        final double heightPaper = (1.25 * 72);
        final double widthPaper = (4 * 72);

        paper.setSize(widthPaper, heightPaper);
        paper.setImageableArea(0, 0, widthPaper, heightPaper);

        pageFormat.setPaper(paper);

        pageFormat.setOrientation(PageFormat.PORTRAIT);


        PrintService[] printService = PrinterJob.lookupPrintServices();

        for (PrintService service : printService) {

            if (service.getName().contains(PRINTER_NAME)) {
                try {
                    printerJob.setPrintService(service);
                    printerJob.setPrintable(new Printable() {
                        @Override
                        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                        {
                            if (pageIndex < getPageNumbers()) {
                                Graphics2D g = (Graphics2D) graphics;
                                g.translate(0, 0);

                                String value;

                                int x = 0;
                                int y = 40;

                                // label under barcode
                                g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), 8));
                                value = barcodeText;
                                g.drawString(" " + value, x + 70, y + 10);


                                InputStream ttf = this.getClass().getResourceAsStream("/3of9.TTF");

                                Font font = null;
                                try {
                                    if (ttf != null) {
                                        font = Font.createFont(Font.TRUETYPE_FONT, ttf);
                                    }
                                } catch (FontFormatException | IOException ex) {
                                    ex.printStackTrace();
                                }

                                g.setFont(font);

                                g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), 24));
                                value = barcode;

                                g.drawString(value, x, y);

                                return PAGE_EXISTS;
                            } else {
                                return NO_SUCH_PAGE;
                            }
                        }
                    }, pageFormat); // The 2nd param is necessary for printing into a label width a right landscape format.
                    printerJob.print();
                } catch (PrinterException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
