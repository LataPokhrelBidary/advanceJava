package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.dataModel.TodoData;
import sample.dataModel.toDoList;

import java.time.LocalDate;

public class Example {

    @FXML
    private TextField ShortDescriptionField;
    @FXML
    private TextArea DetailsArea;
    @FXML
    private DatePicker deadLinePicker;

    public void processResults(){
        String shortDescription = ShortDescriptionField.getText().trim();
        String details = DetailsArea.getText().trim();
        LocalDate deadlineValue = deadLinePicker.getValue();

        TodoData.getInstance().addToDoItem(new toDoList(shortDescription, details, deadlineValue));

    }
}


