package org.example.iss_hospital_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnComenzi;

    @FXML
    private Button btnAdaugaComanda;

    @FXML
    private Button btnComenziPerSectie;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnComenzi) {
            openComenziView();
        }
    }

    @FXML
    private void handleButtonAction2(ActionEvent event) {
        if (event.getSource() == btnAdaugaComanda) {
            adaugaComandaView();
        }
    }



    private void openComenziView() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("comenzi-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Gestionare Comenzi");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adaugaComandaView() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adauga-comanda-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Adauga Comanda");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleButtonAction3(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnComenziPerSectie) {
            openComenziPerSectieView();
        }
    }

    private void openComenziPerSectieView() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("comenzi-per-sectie-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Comenzi Per Sectie");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}