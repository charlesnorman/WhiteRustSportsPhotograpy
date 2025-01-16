package com.whiterustphotography.sports.Controllers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public Label error_lbl;
    public TextField source_directory_fld;
    public TextField destination_directory_fld;
    public TextField name_fld;
    public TextField tag_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        error_lbl.setText("");

        rename_files_btn.setOnAction(_ -> {
            final boolean[] tagFound = {false};
            try {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(source_directory_fld.getText()));
                /*Iterate through each file in the directory*/
                directoryStream.forEach(file -> {
                    File originalFile = new File(file.toString());
                    String originalFileName = originalFile.getName();
                    File renamedFile = new File(destination_directory_fld.getText() +
                            File.separator + name_fld.getText().replaceAll("\s+", "_") + originalFileName.substring(4));
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
                                    if (getCommentTag(tag) == Long.parseLong(tag_fld.getText())) {
                                        try {
                                            Files.copy(originalFile.toPath(),renamedFile.toPath());
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

                }); /*Iterate through each file*/
                if(!tagFound[0]) {
                    error_lbl.setText("Tag " + tag_fld.getText() + " for " + name_fld.getText() + " not found");
                }
                tag_fld.setText("");
                name_fld.setText("");

            } catch (Exception e) {
                error_lbl.setText(e.getMessage());
            }

        });
    }

    public static long getCommentTag(Tag tag) {
        return   Long.parseLong(tag.getDescription());
    }
}