package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.dataModel.TodoData;
import sample.dataModel.toDoList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<toDoList> toDoList;

    @FXML
    private ListView<toDoList> todoListView;
    @FXML
    private TextArea itemDetailTextArea;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;


    public void initialize(){

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<sample.dataModel.toDoList>() {
            @Override
            public void changed(ObservableValue<? extends sample.dataModel.toDoList> observableValue, toDoList oldValue, toDoList newValue) {
                if ( newValue != null){
                    toDoList item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d, yyyy");
                    deadLineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.setItems(TodoData.getInstance().getToDoLists());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();//to select first default

    }
    @FXML
    public void showNewItemDialog(){
        Dialog <ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new ToDo Item");
        dialog.setHeaderText("Use this dialog to create new todo items");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoListDiaglog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        }catch (IOException e){
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;

        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DiaglogControlor controller = fxmlLoader.getController();
            toDoList newItem = controller.processResults();
            todoListView.getSelectionModel().select(newItem);
        }

    }
    @FXML
    public void handleMouseClick(){
        toDoList item =  todoListView.getSelectionModel().getSelectedItem();
        itemDetailTextArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDeadline().toString());

    }

}




