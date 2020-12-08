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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DriverMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button TakeOrderButton;

    @FXML
    private TextField IdField;

    @FXML
    private Button MenuButton;

    @FXML
    private TextArea OrderArea;

    @FXML
    void initialize() throws SQLException {
        MenuButton.setOnAction(event -> {
            change(MenuButton, "sample");
        });
        ArrayList<Order> order = new ArrayList<>();
        order = new DatabaseDriver().getOrder();
        String info = "";
        for (Order order1 : order) {
            info += order1.toString() + "\n";
        }
        OrderArea.setText(info);
        TakeOrderButton.setOnAction(actionEvent -> {
            int id = Integer.parseInt(IdField.getText());
            new DatabaseDriver().deleteOrder(id);
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
