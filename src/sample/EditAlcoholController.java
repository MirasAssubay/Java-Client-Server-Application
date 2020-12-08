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

public class EditAlcoholController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EditButton;

    @FXML
    private TextArea ProductArea;

    @FXML
    private TextField AmountField;

    @FXML
    private TextField IdField;

    @FXML
    private TextField CostField;

    @FXML
    private Button MenuButton;

    @FXML
    void initialize() throws SQLException {
        MenuButton.setOnAction(actionEvent -> {
            change(MenuButton, "adminmenu");
        });
        ArrayList<Alcohol> alcohol = new ArrayList<>();
        alcohol = new DatabaseAlco().getAlco();
        String info = "";
        for (Alcohol alcohol1 : alcohol) {
            info += alcohol1.toString() + "\n";
        }
        ProductArea.setText(info);
        EditButton.setOnAction(actionEvent -> {
            Integer id = Integer.parseInt(IdField.getText());
            Integer cost = Integer.parseInt(CostField.getText());
            Integer amount = Integer.parseInt(AmountField.getText());
            new DatabaseAlco().editAlco(id, amount, cost);
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
