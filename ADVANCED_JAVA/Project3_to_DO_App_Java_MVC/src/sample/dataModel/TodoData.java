package sample.dataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {

    private static TodoData instance = new TodoData();
    private  static String filename ="TodoListItems.txt";

    private ObservableList<toDoList> toDoLists;
    private DateTimeFormatter formatter;

    public static TodoData getInstance(){
        return instance;
    }
    private TodoData(){
        formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
    }

    public ObservableList<toDoList> getToDoLists() {
        return toDoLists;
    }
    public void addToDoItem(toDoList item){
        toDoLists.add(item);

    }


    public void loadtodoList() throws IOException{
        toDoLists = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while ((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");// split the date,userInput and description

                String shortDescription = itemPieces[0];
                String details= itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                toDoList toDoList = new toDoList(shortDescription, details, date);
                toDoLists.add(toDoList);

            }

    }finally {
            if (br != null){
                br.close();
            }

        }
        }
        public void storeTodoList() throws IOException{
        Path path = Paths.get(filename);
            BufferedWriter bw = Files.newBufferedWriter(path);

            try {
                Iterator <toDoList> iter = toDoLists.iterator();
                while (iter.hasNext()){
                    toDoList item = iter.next();
                    bw.write(String.format("%s\t%s\t%s",item.getShortDescription(),
                            item.getDetails(),
                            item.getDeadline().format(formatter)));

                    bw.newLine();

                }

            }finally {
                if(bw != null) {
                    bw.close();
                }
            }
        }


}

