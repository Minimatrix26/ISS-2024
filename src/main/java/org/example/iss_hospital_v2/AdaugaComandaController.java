package org.example.iss_hospital_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.iss_hospital_v2.Model.Comanda;
import org.example.iss_hospital_v2.Repo.ComandaRepo;

public class AdaugaComandaController {

    @FXML
    private TextField idComandaField;

    @FXML
    private TextField sectieIdField;

    @FXML
    private TextField medicamentIdField;

    @FXML
    private TextField cantitateField;

    private ComandaRepo comandaRepo;

    public void initialize() {
        comandaRepo = new ComandaRepo();
    }

    @FXML
    private void handleAdaugaComanda() {
        try {
            int comandaId = Integer.parseInt(idComandaField.getText());
            int sectieId = Integer.parseInt(sectieIdField.getText());
            int medicamentId = Integer.parseInt(medicamentIdField.getText());
            int cantitate = Integer.parseInt(cantitateField.getText());

            Comanda comanda = new Comanda(comandaId, sectieId, medicamentId, cantitate, false);
            comandaRepo.add(comanda);

            // Închide fereastra curentă
            sectieIdField.getScene().getWindow().hide();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
