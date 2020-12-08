package sample;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EditButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button AddButton;

    @FXML
    private Button MenuButton;

    @FXML
    void f20000(ActionEvent event) {

    }

    @FXML
    void initialize() {
        MenuButton.setOnAction(event -> {
            change(MenuButton, "sample");
        });
        AddButton.setOnAction(actionEvent -> {
            change(AddButton, "addalcohol");
        });
        DeleteButton.setOnAction(actionEvent -> {
            change(DeleteButton, "deletealcohol");
        });
        EditButton.setOnAction(actionEvent -> {
            change(EditButton, "editalcohol");
        });

    }
    public void change(Button button, String fxml){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/"+ fxml+".fxml"));


        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
