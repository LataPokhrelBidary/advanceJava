package language.Threads;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/latabidary/Desktop/GIT_UPLOAD/ADVANCED_JAVA/Project4_dbConnection/testjava.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS test1 (name text, phone integer, email text)");
            statement.execute("INSERT INTO test1 VALUES('Bob',12345,'bob@abc.com')");
            statement.execute("INSERT INTO test1 VALUES('Tim',16945,'tim@abc.com')");
            statement.execute("INSERT INTO test1 (name,phone) VALUES('Sara',16945)");
            statement.execute("UPDATE test1 SET email = 'sara1@abc.org' WHERE name = 'Sara'");
            statement.execute("UPDATE test1 SET phone = 6822198066  WHERE name = 'Tim'");
            statement.execute("UPDATE test1 SET phone = 8172198060  WHERE name = 'Bob'");

//            statement.execute("CREATE TABLE IF NOT EXISTS test2 (name text, ID integer, grade integer)");
//            statement.execute("INSERT INTO test2 VALUES('Lisa',301,92)");
//            statement.execute("INSERT INTO test2 VALUES('Jack',302,95)");
//            statement.execute("INSERT INTO test2 (name,ID) VALUES('Simon',303)");
//            statement.execute("UPDATE test2 SET grade = 92 where ID = 303");
//            statement.execute("DELETE test2 WHERE ID = 303");
//





            statement.close();
            conn.close();


        }catch (SQLException e){
            System.out.println("Something went wrong "+ e.getMessage());
        }


    }
}
