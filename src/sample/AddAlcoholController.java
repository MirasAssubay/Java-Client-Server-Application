package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddAlcoholController {
    @FXML
    private Button exitButton;

    @FXML
    private ComboBox<String> typeComboBox;
    private ObservableList<String> types = FXCollections.observableArrayList();
    private ArrayList<String> types_al = new ArrayList<>();

    @FXML
    private ComboBox<Integer> strComboBox;
    private ObservableList<Integer> strs = FXCollections.observableArrayList();
    private ArrayList<Integer> str_al = new ArrayList<>();

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextField costTextField;

    @FXML
    public void initialize(){
        str_al.add(7);
        str_al.add(12);
        str_al.add(24);
        str_al.add(32);
        str_al.add(40);
        str_al.add(50);
        strs.setAll(str_al);
        strComboBox.setItems(strs);
        types_al.add("VODKA");
        types_al.add("BEER");
        types_al.add("WHISKEY");
        types_al.add("WINE");
        types_al.add("GIN");
        types_al.add("RUM");
        types.setAll(types_al);
        typeComboBox.setItems(types);
        addButton.setOnAction(actionEvent -> {
            String type = typeComboBox.getSelectionModel().getSelectedItem();
            int str = strComboBox.getSelectionModel().getSelectedItem();
            Integer cost = Integer.parseInt(costTextField.getText());
            Integer amount = Integer.parseInt(amountTextField.getText());
            String name = nameTextField.getText();
            Alcohol alcohol = new Alcohol(name, str, type, amount, cost);
            new DatabaseAlco().AddAlcohol(alcohol);
        });
        exitButton.setOnAction(actionEvent -> {
            change(exitButton, "adminmenu");
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
