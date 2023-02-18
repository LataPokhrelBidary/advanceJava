package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class Controller {
    @FXML
    public TextField name;
    @FXML
    public TextField street;
    @FXML
    public TextField city;
    @FXML
    public TextField state;
    @FXML
    public TextField zip;
    @FXML
    public TextField title;
    @FXML
    public TextField purchase_date;
    @FXML
    public TextField acc_no;
    @FXML
    public CheckBox appButton;
    @FXML
    public CheckBox musicButton;
    @FXML
    public ComboBox chooseOne;
    @FXML
    private HBox musicType;
    @FXML
    public Label typeMusic;
    @FXML
    public Label tm;
    @FXML
    javafx.scene.control.ToggleGroup ToggleGroup;

    String n, s, c, st, z, choose_one, app_btn, m_btn, t, p_date, ac_no;
    // creating new alert object
    Alert alert = new Alert(Alert.AlertType.NONE);
    // for file to create
    String file_name;


    public void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void dataProcess() {
        String[] customer = new String[12];     // creating customer array

        // get text from name field store it to n
        n = name.getText();

        s = street.getText();

        c = city.getText();

        st = state.getText();

        z = zip.getText();

        app_btn = appButton.getText();

        m_btn = musicButton.getText();

        choose_one = chooseOne.getValue().toString();


        RadioButton radioButton = (RadioButton) ToggleGroup.getSelectedToggle();


        t = title.getText();
        p_date = purchase_date.getText();
        ac_no = acc_no.getText();


        if (n.isEmpty()) {
            showAlert("Enter your name!!");
            name.requestFocus();
        } else if (s.isEmpty()) {
            showAlert("Enter your street!!");
            street.requestFocus();
        } else if (c.isEmpty()) {
            showAlert("Enter your city!!");
            city.requestFocus();
        } else if (st.isEmpty()) {
            showAlert("Enter your state!!");
            state.requestFocus();
        } else if (z.isEmpty()) {
            showAlert("Enter your zip!!");
            zip.requestFocus();
        } else if (!appButton.isSelected() && !musicButton.isSelected()) {
            showAlert("YOU need to Choose  one Music or App");
            appButton.requestFocus();
        } else if (appButton.isSelected() && musicButton.isSelected()) {
            showAlert("Select Choose One Music or App");
            appButton.requestFocus();
        } else if (radioButton == null)
       {
           showAlert("Select Type of music");

        } else if (t.isEmpty()) {
            showAlert("Enter your Title!!");
            title.requestFocus();
        } else if (p_date.isEmpty()) {
            showAlert("Enter your Purchase Date!!");
            purchase_date.requestFocus();
        } else if (ac_no.isEmpty()) {
            showAlert("Enter your Accoutn no!!");
            acc_no.requestFocus();
        } else {
//
            if (appButton.isSelected()) {

                customer[6] = app_btn;
                file_name = "app.txt";

            } if (musicButton.isSelected()) {
                customer[6] = m_btn;
                file_name = "music.txt";
            }

            customer[0] = n;                      // name
            customer[1] = s;                      // street
            customer[2] = c;                      // city
            customer[3] = st;                     // state
            customer[4] = z;                      // zip
            customer[5] = chooseOne.getValue().toString();
            customer[7] = radioButton.getText();  // Type of app
            customer[8] = t;                      // title
            customer[9] = p_date;                 // purchase date
            customer[10] = ac_no;

            createFile(customer);               // creating file

        }

    }

    private void createFile(String[] customer) {
        Path path = Paths.get(file_name);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= 11; i++)
            stringBuilder.append(customer[i] + "\t\t");
        stringBuilder.append("\n");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearField();
        name.requestFocus();

    }

    public void showAlert(String text) {
        alert.setAlertType(Alert.AlertType.ERROR);

        alert.setContentText(text);
        alert.show();
    }

    public void clearField() {
        name.clear();                                        // name field clear
        street.clear();                                      // street field clear
        city.clear();                                        // city field clear
        state.clear();                                       // state field clear
        zip.clear();                                         // zip field clear
        appButton.setSelected(false);
        musicButton.setSelected(false);
        chooseOne.getSelectionModel().select(0);
        ToggleGroup.getSelectedToggle().setSelected(false);
        title.clear();                                      // title field clear
        purchase_date.clear();                              // purchase date field clear
        acc_no.clear();                                     // account no field clear

    }

    public void toggleMusic() {

        if (appButton.isSelected()) {
            chooseOne.setDisable(true);
            typeMusic.setDisable(true);
        } else {
            chooseOne.setDisable(false);
            typeMusic.setDisable(false);
        }
    }

    public void toggleApp() {
        if (musicButton.isSelected()) {
            tm.setDisable(true);
            musicType.setDisable(true);
        } else {
            tm.setDisable(false);
            musicType.setDisable(false);
        }

    }
}
