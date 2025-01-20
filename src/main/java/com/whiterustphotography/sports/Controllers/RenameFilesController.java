package com.whiterustphotography.sports.Controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class RenameFilesController implements Initializable {


    public Button rename_files_btn;
    public TextArea error_tfld;
    public TextField source_directory_fld;
    public TextField destination_directory_fld;
    public TextField name_fld;
    public TextField tag_fld;
    public Button src_file_chooser_btn;
    public Button dst_file_chooser_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        error_tfld.setText("");
        DirectoryChooser directoryChooser = new DirectoryChooser();

        src_file_chooser_btn.setOnAction(_ -> {
            File file = directoryChooser.showDialog(null);
            if (file != null) {
                source_directory_fld.setText(file.getAbsolutePath());
            }
        });

        dst_file_chooser_btn.setOnAction(_ -> {
            File file = directoryChooser.showDialog(null);
            if (file != null) {
                destination_directory_fld.setText(file.getAbsolutePath());
            }
        });

        rename_files_btn.setOnAction(_ -> {
            final boolean[] tagFound = {false};
            try {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(source_directory_fld.getText()));

                /*Start iterate through each source file*/
                directoryStream.forEach(file -> {
                    File originalFile = new File(file.toString());
                    String originalFileName = originalFile.getName();
                    File renamedFile = new File(destination_directory_fld.getText() +
                            File.separator +
                            name_fld.getText().replaceAll("\s+", "_") + "_"
 +                            originalFileName.substring(4) );
                    Metadata metadata;

                    try {
                        metadata = ImageMetadataReader.readMetadata(originalFile);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if (metadata != null) {
                        for (Directory directory : metadata.getDirectories()) {
                            for (Tag tag : directory.getTags()) {
                                if (tag.toString().contains("User Comment")) {
                                    //System.out.println(tag.getDescription());
                                    if (getCommentTagAsLong(tag) == getTagFieldAsLong(tag_fld.getText(), name_fld.getText())) {
                                        try {
                                            Files.copy(originalFile.toPath(), renamedFile.toPath());
                                            tagFound[0] = true;
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        System.out.println(renamedFile.getName());
                                    }
                                }
                            }
                        }
                    }
                }); /* End Iterate through each source file*/

                if (!tagFound[0]) {
                    error_tfld.setText("Tag " + tag_fld.getText() + " for " + name_fld.getText() + " not found");
                }
            } catch (Exception e) {
                error_tfld.setText(e.getMessage());
            } finally {
                tag_fld.setText("");
                name_fld.setText("");
            }

        });
    }

    private static long getCommentTagAsLong(Tag tag) {
        long result;
        try {
            result = Long.parseLong(tag.getDescription());
        } catch (NumberFormatException e) {
            throw new RuntimeException(

                    "Illegal photo tag: " + e.getMessage() + "\n" + e);
        }
        return result;
    }

    private static long getTagFieldAsLong(String number, String name) {
        long result;
        try {
            result = Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Illegal input tag for " + name + "\n" + e.getMessage() + "\n" + e);
        }
        return result;
    }
}