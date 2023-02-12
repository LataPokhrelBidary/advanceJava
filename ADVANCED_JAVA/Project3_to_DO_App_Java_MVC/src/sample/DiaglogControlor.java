package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.dataModel.TodoData;
import sample.dataModel.toDoList;

import java.time.LocalDate;

public class DiaglogControlor {
    @FXML
    private TextField ShortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadLinePicker;

    public toDoList processResults(){
        String shortDescription = ShortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadLinePicker.getValue();

        toDoList newItem = new toDoList(shortDescription, details, deadlineValue);
        TodoData.getInstance().addToDoItem(newItem);
        return newItem;

    }
}

