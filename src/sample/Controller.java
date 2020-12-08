package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignInButton;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button RegistrationButton;
    @FXML
    private TextField PasswordField;
    private ArrayList<User> users;
    private ClientHandler clientHandler = new ClientHandler();
    @FXML
    void initialize() throws SQLException, IOException {
        clientHandler.writeRequest("GET_USERS");
        if (clientHandler.checkRequest("USERS_GOT")){
            users = ClientHandler.users;
        }

        for (User user : users) {
            System.out.println(user + " " + user.getRole());
        }
        RegistrationButton.setOnAction(event -> {
            RegistrationButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/registration.fxml"));


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
        SignInButton.setOnAction(event -> {
            String role = "";
            FXMLLoader loader = new FXMLLoader();
            String username = UsernameField.getText().trim();
            String password = PasswordField.getText().trim();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                    role = user.getRole();
                }
            }
            if (role.equals("admin")){
                SignInButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/sample/adminmenu.fxml"));
            }
            else if (role.equals("user")) {
                SignInButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/sample/menu.fxml"));
            }
            else if (role.equals("driver")) {
                SignInButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/sample/drivermenu.fxml"));
            }
            else {
                return;
            }
            try{
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
