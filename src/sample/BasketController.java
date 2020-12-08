package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static sample.MenuController.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BasketController {

    @FXML
    private TextField addressfield;
    @FXML
    private Button exitButton;

    @FXML
    private TextArea basketTextArea;

    @FXML
    private TextField amountfield;

    @FXML
    private TextField idfield;


    @FXML
    private Button buyButton;
    @FXML
    void initialize() throws SQLException {
        exitButton.setOnAction(actionEvent -> {
            change(exitButton, "menu");
        });
        ArrayList<Alcohol> alcohol = new DatabaseAlco().getAlco();
        String info = "";
        for (Alcohol alcohol1: alcohol) {
            if (alcohol1.isBasket()) {
                info += alcohol1.toString();
            }
        }
        basketTextArea.setText(info);
        buyButton.setOnAction(actionEvent -> {
            int id = Integer.parseInt(idfield.getText());
            int amount = Integer.parseInt(amountfield.getText());
            double payment = amount * alcohol.get(id).getCost() * 0.1;
            String address = addressfield.getText().trim();
            Order order = new Order(alcohol.get(id).getName(), alcohol.get(id).getStrength(),alcohol.get(id).getType(),amount,alcohol.get(id).getCost(), payment,address);
            new DatabaseDriver().addOrder(order);
            new DatabaseAlco().updateAlco(id, amount);
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
