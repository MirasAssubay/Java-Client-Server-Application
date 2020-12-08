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
import javafx.scene.control.*;
import javafx.stage.Stage;
public class MenuController {

    @FXML
    private Button BasketButton;

    @FXML
    private TextField IdField;

    @FXML
    private Button MenuButton;

    @FXML
    private Button BeerButton;

    @FXML
    private Button WhiskeyButton;

    @FXML
    private TextArea ProductArea;

    @FXML
    private TextField SearchField;

    @FXML
    private Button GinButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button VodkaButton;

    @FXML
    private Button AddBasketButton;

    @FXML
    private Button WineButton;

    @FXML
    private Button RumButton;
    @FXML
    void initialize() throws SQLException {
        MenuButton.setOnAction(event -> {
            change(MenuButton, "sample");

        });
        BasketButton.setOnAction(event -> {
            change(BasketButton, "basket");
        });
        ArrayList<Alcohol> alcohol = new ArrayList<>();
        alcohol = new DatabaseAlco().getAlco();
        String info = "";
        for (Alcohol alcohol1 : alcohol) {
            info += alcohol1.toString() + "\n";
        }
        ProductArea.setText(info);
        VodkaButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getVodka();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        BeerButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getBeer();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        WhiskeyButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getWhiskey();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        WineButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getWine();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        RumButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getRum();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        GinButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcoholv = new ArrayList<>();
            try {
                alcoholv = new DatabaseAlco().getGin();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String infov = "";
            for (Alcohol alcoholv1: alcoholv) {
                infov += alcoholv1.toString() + "\n";
            }
            ProductArea.setText(infov);
        });
        SearchButton.setOnAction(actionEvent -> {
            String search = SearchField.getText().trim();
            ArrayList<Alcohol> searchv = new ArrayList<>();
            String infov = "";
            try {
                searchv = new DatabaseAlco().searchAlco(search);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (Alcohol searchv1: searchv) {
                infov += searchv1.toString();
            }
            ProductArea.setText(infov);
        });
        AddBasketButton.setOnAction(actionEvent -> {
            ArrayList<Alcohol> alcohol2 = null;
            try {
                alcohol2 = new DatabaseAlco().getAlco();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int id = Integer.parseInt(IdField.getText());
            alcohol2.get(id).setBasket(true);
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
