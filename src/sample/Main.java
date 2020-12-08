package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    public static Socket socket;
    public static void connectToServer(){
        try {
            socket=new Socket("127.0.0.1",3460);
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        connectToServer();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Authorization");
        primaryStage.setScene(new Scene(root, 821, 400));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
