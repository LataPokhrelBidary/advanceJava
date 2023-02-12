package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dataModel.TodoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TodoData.getInstance().storeTodoList();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    //load information from textFile .txt


    @Override
    public void init() throws Exception {
        try {
            TodoData.getInstance().loadtodoList();

        }catch (IOException e){
            System.out.println(e.getMessage());

        }
    }
}
