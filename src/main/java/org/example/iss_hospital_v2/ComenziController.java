package org.example.iss_hospital_v2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.iss_hospital_v2.Model.Comanda;
import org.example.iss_hospital_v2.Repo.ComandaRepo;

public class ComenziController {
    @FXML
    private TableView<Comanda> tableComenzi;

    @FXML
    private TableColumn<Comanda, Integer> colId;

    @FXML
    private TableColumn<Comanda, Integer> colSectieId;

    @FXML
    private TableColumn<Comanda, Integer> colMedicamentId;

    @FXML
    private TableColumn<Comanda, Integer> colCantitate;

    @FXML
    private TableColumn<Comanda, Boolean> colOnorata;

    @FXML
    private Button btnOnoreaza;

    private ComandaRepo comandaRepo;

    @FXML
    private void initialize() {
        comandaRepo = new ComandaRepo();
        loadComenzi();
    }

    private void loadComenzi() {
        ObservableList<Comanda> comenzi = FXCollections.observableArrayList(comandaRepo.getAll());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSectieId.setCellValueFactory(new PropertyValueFactory<>("sectieId"));
        colMedicamentId.setCellValueFactory(new PropertyValueFactory<>("medicamentId"));
        colCantitate.setCellValueFactory(new PropertyValueFactory<>("cantitate"));
        colOnorata.setCellValueFactory(new PropertyValueFactory<>("onorata"));
        tableComenzi.setItems(comenzi);
    }

    @FXML
    private void handleOnoreaza() {
        Comanda selectedComanda = tableComenzi.getSelectionModel().getSelectedItem();
        if (selectedComanda != null && !selectedComanda.isOnorata()) {
            try {
                comandaRepo.update(selectedComanda.getId());
                loadComenzi();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
