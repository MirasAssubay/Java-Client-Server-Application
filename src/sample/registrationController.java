package sample;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registrationController {
    @FXML
    private TextField usernameField;

    @FXML
    private Button registrationButton;


    @FXML
    private Button MenuButton;

    @FXML
    private TextField passwordField;

    private ClientHandler clientHandler = new ClientHandler();
    @FXML
    void initialize() {
        MenuButton.setOnAction(event -> {
            MenuButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/registration.fxml"));
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        registrationButton.setOnAction(actionEvent -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = new User(username,password);
            try {
                clientHandler.writeRequest("ADD_USER", user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (clientHandler.checkRequest("SUCCESSFULLY")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("SUCCESSFULLY");
                alert.showAndWait();
                registrationButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/sample.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("SOMETHING GOES WRONG");
                alert.showAndWait();
            }

        });
}
}
